package otokatari.com.otokatari.Model.Player;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  PlayerLoopMode
{
    LOOP_PLAY_LIST(0),
    LOOP_SINGLE(1),
    RANDOM(2);

    private int Number;

}
