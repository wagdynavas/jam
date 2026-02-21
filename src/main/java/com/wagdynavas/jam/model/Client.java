package com.wagdynavas.jam.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "CLIENT")
public class Client {

    @Id
    @Column(name = "CLIENT_ID")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "X_CLIENT_ID", nullable = false, unique = true)
    private String xClientId;

    @Column(name = "X_API_KEY_DEV1")
    private String dev1Key;

    @Column(name = "X_API_KEY_PROD")
    private String prodKey;

    @Column(name = "CLIENT_CREATE_AT")
    private Timestamp createAt;
    @Column(name = "CLIENT_LAST_UPDATE")
    private Timestamp lastUpdate;

    @Column(name = "CLIENT_RATE_LIMIT")
    private int rateLimit;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "CLIENT_ROLE")
    private Role role = Role.CLIENT;

}
