package com.example.codinqquiz.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;

import org.hibernate.type.SqlTypes;
import java.time.LocalDateTime;


@TypeDef({
        @TypeDef(name = "string-array", typeClass = StringArrayType.class),
        @TypeDef(name = "int-array", typeClass = IntArrayType.class),
        @TypeDef(name = "json", typeClass = JsonStringType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_quiz")
public class UserQuiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id")
    protected int id;

    @ManyToOne(/*cascade = CascadeType.ALL*/)
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne(/*cascade = CascadeType.ALL*/)
    @JoinColumn(name="quiz_id", nullable=false)
    private Quiz quiz;

    @Column
    private int score;

    @JdbcTypeCode(SqlTypes.JSON)
    private MyJson

    @Column
    private LocalDateTime created ;

    @PrePersist
    public void onCreate(){
        this.created = LocalDateTime.now();
    }

}
