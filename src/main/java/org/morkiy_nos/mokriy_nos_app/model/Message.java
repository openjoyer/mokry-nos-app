package org.morkiy_nos.mokriy_nos_app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.type.NumericBooleanConverter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Выберите опцию")
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type")
    private MessageType type;

    @Column(name = "title")
    @NotEmpty(message = "Это поле обязательно")
    @Length(max = 50, message = "Максимум - 50 символов")
    private String title;

    @NotEmpty(message = "Это поле обязательно")
    @Length(max = 1000, message = "Текст должен быть до 1000 символов")
    private String text;

    @Column(name = "created_date")
    @Basic
    private LocalDateTime createdDate;

    @Column(name = "person_name")
    @Pattern(regexp = "[A-я]+ [A-я]+[A-я ]*", message = "Введите ФИО через пробел")
    private String personName;

    @Column(name = "is_marked")
    @Convert(converter = NumericBooleanConverter.class)
    private boolean isMarked;
}
