package com.nbe2.domain.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.nbe2.domain.emergencyroom.EmergencyRoom;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "medical_person_infos")
public class MedicalPersonInfo {

    @Id
    @Column(name = "medical_person_info_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @MapsId
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private TempFile license;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emergency_room_id")
    private EmergencyRoom emergencyRoom;

    public static MedicalPersonInfo create() {
        return new MedicalPersonInfo();
    }

    public void assignParent(User user, EmergencyRoom emergencyRoom) {
        this.user = user;
        this.emergencyRoom = emergencyRoom;
    }
}
