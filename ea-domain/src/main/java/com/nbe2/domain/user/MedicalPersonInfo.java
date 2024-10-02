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
import com.nbe2.domain.file.FileMetaData;

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
    private FileMetaData license;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emergency_room_id")
    private EmergencyRoom emergencyRoom;

    private MedicalPersonInfo(User user, EmergencyRoom emergencyRoom, FileMetaData license) {
        this.user = user;
        this.emergencyRoom = emergencyRoom;
        this.license = license;
    }

    public static MedicalPersonInfo of(
            User user, EmergencyRoom emergencyRoom, FileMetaData license) {
        return new MedicalPersonInfo(user, emergencyRoom, license);
    }
}
