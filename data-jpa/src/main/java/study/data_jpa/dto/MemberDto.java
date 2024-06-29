package study.data_jpa.dto;

import lombok.Data;

@Data
public class MemberDto {

    private Long id;
    private String name;
    private String teamName;

    public MemberDto(Long id, String username, String teamName) {
        this.id = id;
        this.name = username;
        this.teamName = teamName;
    }
}
