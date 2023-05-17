package home.user.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
    String id;
    String pwd;
    String name;
    String address;
    String phone;
}
