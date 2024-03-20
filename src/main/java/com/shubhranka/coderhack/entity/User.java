package com.shubhranka.coderhack.entity;

import com.mongodb.lang.NonNull;
import com.mongodb.lang.Nullable;
import com.shubhranka.coderhack.enums.Badge;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;


@Document(collection = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    private String userId;

    @NonNull
    private String userName;
    private byte score = 0;

    @Nullable
    private ArrayList<String> badges;

}
