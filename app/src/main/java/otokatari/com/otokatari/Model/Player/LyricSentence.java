package otokatari.com.otokatari.Model.Player;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LyricSentence implements Serializable
{
    private long Mills;
    private String Text;

    public LyricSentence(long mills, String text)
    {
        Mills = mills;
        Text = text;
    }
}
