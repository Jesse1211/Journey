
// input 给了两个 String 数组，
// 第一个数组给的是歌曲和时长的关系，格式为 <songName>:<SongLength>，
// 第二个数组给的是 animation 和时长的关系，格式为 <animationName>:<animationLength>，
// 然后要求找出每个歌曲可以用哪个animation * n 组成（就是歌曲的长度可以整除 animation 的长度），
// 如果有多个取 index 小的，输出内容为一个 String 数组，格式为 <animationName>:<SongLength/animationLength>。
// 要求有序输出
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<String> longestCommonSubsequence(String[] song, String[] animation) {
        Map<String, Integer> songMap = new HashMap<>();
        for (int i = 0; i < song.length; i++) {
            String[] songInfo = song[i].split(":");
            String songName = songInfo[0];
            int songLength = Integer.parseInt(songInfo[1]);
            songMap.put(songName, songLength);
        }

        Map<String, Integer> animationMap = new HashMap<>();
        for (int i = 0; i < animation.length; i++) {
            String[] animationInfo = animation[i].split(":");
            String animationName = animationInfo[0];
            int animationLength = Integer.parseInt(animationInfo[1]);
            animationMap.put(animationName, animationLength);
        }

        List<String> res = new ArrayList<>();
        for (String songName : songMap.keySet()) {
            int songLength = songMap.get(songName);
            for (String animationName : animationMap.keySet()) {
                int animationLength = animationMap.get(animationName);
                if (songLength % animationLength == 0) {
                    res.add(animationName + ":" + songLength / animationLength);
                    break;
                }
            }
        }
        return res;
    }
}
