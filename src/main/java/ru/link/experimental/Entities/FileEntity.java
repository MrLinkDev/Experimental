package ru.link.experimental.Entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.*;

@Data
@Entity
@Table(name = "file", schema = "experimental_app", catalog = "experimental")
@AllArgsConstructor
@NoArgsConstructor
public class FileEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "mime_type")
    private String mimeType;

    @Column(name = "size")
    private long size;

    @Column(name = "hash")
    private String hash;

    public void setHash() throws NoSuchAlgorithmException {
        String modifiedName = new StringBuilder(this.name).append(this.mimeType).append(this.size).append(new Date().getTime()).toString();
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(modifiedName.getBytes(StandardCharsets.UTF_8));
        this.hash = new BigInteger(1, messageDigest.digest()).toString(16);
    }

}
