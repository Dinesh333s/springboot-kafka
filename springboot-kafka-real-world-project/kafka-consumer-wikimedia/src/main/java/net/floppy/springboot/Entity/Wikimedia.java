package net.floppy.springboot.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.processing.Generated;


@Setter
@Getter
@Entity
@Table(name = "wikimedia_recentChange")
public class Wikimedia {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    //    @Lob //this annotation is to indicate that the incoming data is big
    @Column(columnDefinition = "LONGTEXT")
    private String Event;
}
