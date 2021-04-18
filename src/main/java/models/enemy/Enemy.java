package models.enemy;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Enemy {

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
