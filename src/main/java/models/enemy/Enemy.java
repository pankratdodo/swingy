package models.enemy;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Enemy implements Serializable {

    String race;

    int attack;

    int defence;

    int actualHp;

    int maxHp;

    int x;

    int y;

    String artefactName;

    int artefactAttack;
}
