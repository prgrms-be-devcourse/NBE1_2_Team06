<template>
  <div class="container">
    <input v-model="myLocation" class="input" placeholder="현재 위치 (예: 126.906775, 37.516743)" disabled />
    <input v-model="hospitalName" class="input" placeholder="병원 이름 (예: 서울대학교치과병원)" />
    <button @click="fetchRoute" class="button">경로 찾기</button>

    <!-- 색상 설명 추가 -->
    <div class="legend">
      <div class="legend-item">
        <div class="color-box green"></div>
        <span>원활</span>
      </div>
      <div class="legend-item">
        <div class="color-box orange"></div>
        <span>혼잡</span>
      </div>
      <div class="legend-item">
        <div class="color-box red"></div>
        <span>매우 혼잡</span>
      </div>
    </div>

    <!-- 총 소요 시간 표시 -->
    <div v-if="totalDuration" class="total-time">
      총 소요 시간: {{ formattedTotalDuration }}
    </div>

    <!-- 섹션 정보 표시 -->
    <div v-if="sectionsSummary.length" class="sections-summary">
      <h3>구간별 상세 정보</h3>
      <ul>
        <li v-for="(section, index) in sectionsSummary" :key="index" class="section-item">
          <strong>{{ section.name || 'Unnamed Road' }}</strong><br />
          거리: {{ section.distance }}m, 혼잡도: {{ section.congestionText }}, 평균 속도: {{ section.speed }} km/h
        </li>
      </ul>
    </div>

    <div id="map" class="map"></div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      myLocation: '126.906775,37.516743',
      hospitalName: '',
      map: null,
      polyline: null,
      markers: [],
      startMarker: null,
      goalMarker: null,
      sectionPolylines: [],
      activeInfoWindow: null,
      totalDuration: 0,
      sectionsSummary: []
    };
  },
  computed: {
    formattedTotalDuration() {
      const minutes = Math.floor(this.totalDuration / 60);
      const seconds = Math.floor(this.totalDuration % 60);
      return `${minutes}분 ${seconds}초`;
    }
  },
  mounted() {
    this.initMap();
  },
  methods: {
    initMap() {
      const mapOptions = {
        center: new naver.maps.LatLng(37.516743, 126.906775),
        zoom: 10
      };
      this.map = new naver.maps.Map("map", mapOptions);
    },
    async fetchRoute() {
      const url = `http://localhost:8080/api/v1/emergency-rooms/directions?myLocation=${this.myLocation}&hospitalName=${this.hospitalName}`;
      try {
        const response = await axios.get(url);
        const routeData = response.data.result.route.traoptimal[0];

        this.totalDuration = routeData.summary.duration / 1000;

        this.sectionsSummary = routeData.section.map(section => ({
          name: section.name,
          distance: section.distance,
          congestionText: this.getCongestionText(section.congestion),
          speed: section.speed
        }));

        this.displayRoute(routeData.path);
        this.displayStartAndGoalMarkers(routeData.summary.start.location, routeData.summary.goal.location);
        this.displaySections(routeData.path, routeData.section);
        this.displayGuides(routeData.guide);
      } catch (error) {
        console.error("경로를 가져오는 데 실패했습니다.", error);
      }
    },
    displayRoute(path) {
      if (this.polyline) this.polyline.setMap(null);
      const latLngs = path.map(coord => new naver.maps.LatLng(coord[1], coord[0]));
      this.polyline = new naver.maps.Polyline({
        path: latLngs,
        strokeColor: "#5347AA",
        strokeWeight: 5,
        map: this.map
      });
      const bounds = new naver.maps.LatLngBounds();
      latLngs.forEach(latLng => bounds.extend(latLng));
      this.map.fitBounds(bounds);
    },
    displayStartAndGoalMarkers(start, goal) {
      if (this.startMarker) this.startMarker.setMap(null);
      if (this.goalMarker) this.goalMarker.setMap(null);

      this.startMarker = new naver.maps.Marker({
        position: new naver.maps.LatLng(goal[1], goal[0]),
        map: this.map,
        title: "출발지",
        icon: { content: '<div style="color: blue; font-size: 14px;">🚩</div>', anchor: new naver.maps.Point(12, 12) }
      });

      this.goalMarker = new naver.maps.Marker({
        position: new naver.maps.LatLng(start[1], start[0]),
        map: this.map,
        title: "목적지",
        icon: { content: '<div style="color: red; font-size: 14px;">🏁</div>', anchor: new naver.maps.Point(12, 12) }
      });
    },
    displaySections(path, sections) {
      this.sectionPolylines.forEach(polyline => polyline.setMap(null));
      this.sectionPolylines = [];
      sections.forEach(section => {
        const sectionPath = path.slice(section.pointIndex, section.pointIndex + section.pointCount);
        const latLngs = sectionPath.map(coord => new naver.maps.LatLng(coord[1], coord[0]));
        const polyline = new naver.maps.Polyline({
          path: latLngs,
          strokeColor: this.getSectionColor(section.congestion),
          strokeWeight: 5,
          map: this.map
        });
        this.sectionPolylines.push(polyline);
        const infoWindow = new naver.maps.InfoWindow({
          content: `<div style="padding:5px;">${section.name}<br>거리: ${section.distance}m<br>속도: ${section.speed}km/h</div>`
        });
        naver.maps.Event.addListener(polyline, "click", e => {
          infoWindow.setPosition(e.coord);
          infoWindow.open(this.map);
        });
      });
    },
    getCongestionText(congestion) {
      switch (congestion) {
        case 1:
          return "원활";
        case 2:
          return "서행";
        case 3:
          return "혼잡";
        default:
          return "값없음";
      }
    },
    getSectionColor(congestion) {
      switch (congestion) {
        case 1:
          return "#00FF00";
        case 2:
          return "#FFA500";
        case 3:
          return "#FF0000";
        default:
          return "#0000FF";
      }
    },
    displayGuides(guide) {
      this.markers.forEach(marker => marker.setMap(null));
      this.markers = [];

      for (let i = guide.length - 1; i >= 0; i--) {
        const step = guide[i];
        const index = guide.length - 1 - i;
        const pathLength = this.polyline.getPath().getLength();
        const latLng = this.polyline.getPath().getAt((i * Math.floor(pathLength / guide.length)) % pathLength);

        const marker = new naver.maps.Marker({
          position: latLng,
          map: this.map,
          title: step.instructions,
          icon: {
            content: `<div style="color: black; background-color: #FFF; border-radius: 50%;
                          padding: 2px 5px; font-size: 10px; border: 1px solid #333;">
                          ${index + 1}
                    </div>`,
            anchor: new naver.maps.Point(8, 8)
          }
        });

        this.markers.push(marker);

        const infoWindow = new naver.maps.InfoWindow({
          content: `<div style="padding:5px; font-size: 10px">
                  <strong>방향:</strong> ${step.instructions}<br>
                  <strong>거리:</strong> ${step.distance}m<br>
                  <strong>시간:</strong> ${Math.floor(step.duration / 1000)}초
                </div>`
        });

        naver.maps.Event.addListener(marker, "click", () => {
          if (this.activeInfoWindow) {
            this.activeInfoWindow.close();
          }
          infoWindow.open(this.map, marker);
          this.activeInfoWindow = infoWindow;
        });
      }

      naver.maps.Event.addListener(this.map, "click", () => {
        if (this.activeInfoWindow) {
          this.activeInfoWindow.close();
          this.activeInfoWindow = null;
        }
      });
    }
  }
};
</script>

<style>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  background-color: #f8f9fa;
  border-radius: 10px;
  box-shadow: 0px 8px 16px rgba(0, 0, 0, 0.2);
}
.input {
  width: 100%;
  padding: 10px;
  margin: 10px 0;
  border: 1px solid #ced4da;
  border-radius: 5px;
  font-size: 16px;
}
.button {
  width: 100%;
  padding: 10px;
  margin-top: 10px;
  background-color: #007bff;
  color: white;
  font-size: 16px;
  font-weight: bold;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}
.button:hover {
  background-color: #0056b3;
}
.legend {
  display: flex;
  justify-content: space-between;
  width: 100%;
  margin: 15px 0;
  padding: 10px;
  border-radius: 10px;
  background-color: #f1f3f5;
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
}
.legend-item {
  display: flex;
  align-items: center;
  font-size: 14px;
}
.color-box {
  width: 15px;
  height: 15px;
  margin-right: 8px;
  border-radius: 3px;
}
.green { background-color: #00FF00; }
.orange { background-color: #FFA500; }
.red { background-color: #FF0000; }
.total-time {
  font-size: 18px;
  font-weight: bold;
  color: #343a40;
  margin: 15px 0;
  padding: 10px;
  background-color: #e9ecef;
  border-radius: 10px;
  text-align: center;
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
}
.sections-summary {
  width: 100%;
  background-color: #ffffff;
  padding: 15px;
  border-radius: 10px;
  margin: 15px 0;
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
}
.sections-summary h3 {
  margin-bottom: 10px;
  color: #495057;
}
.sections-summary ul {
  list-style: none;
  padding: 0;
  font-size: 14px;
}
.section-item {
  margin-bottom: 8px;
  padding: 8px;
  background-color: #f8f9fa;
  border-radius: 5px;
}
.map {
  width: 100%;
  height: 500px;
  border-radius: 10px;
  margin-top: 20px;
}
</style>
