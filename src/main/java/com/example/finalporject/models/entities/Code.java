package com.example.finalporject.models.entities;

import com.example.finalporject.models.enums.CodeStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Code {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String code;
    @CreationTimestamp
    Date startDate;
    Date endDate;
    @Enumerated(value = EnumType.STRING)
    CodeStatus codeStatus;
    @ManyToOne
    @JoinColumn(name = "id_user")
    User userId;
}
