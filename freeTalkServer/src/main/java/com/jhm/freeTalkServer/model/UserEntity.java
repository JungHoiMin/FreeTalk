package com.jhm.freeTalkServer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "tel")}, name = "FTUser")
public class UserEntity {
    @Id
    private String tel;
    @Column
    private String passwd;
    @Column
    private String name;
}
