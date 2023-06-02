package Atlassian;
import java.util.*;
import java.util.stream.Collectors;

public class RankByVote {


    public static void main(String[] args) {
        System.out.println(rankByVote(new String[]{"ABC","ACB","ABC","ACB","ACB"}));
    }
    public static String rankByVote(String[] votes)
    {
        Map<Character,Team> teams = new HashMap<>();
        for(String vote:votes)
        {
            for(int i =0;i< 3;i++)
            {
                Character team = vote.charAt(i);
                teams.putIfAbsent(team, new Team(team));
                Team teamObject = teams.get(team);
                teamObject.vote[i]+=1;
            }
        }
        List<Team> teamList = teams.entrySet().stream().map(i->i.getValue()).collect(Collectors.toList());
        TeamComparator teamComparator = new FirstPositionComparator(new SecondPositionComparator(new ThirdPositionComparator(new StringComparator(null))));
        teamList.sort(teamComparator);
        StringBuilder stringBuilders = new StringBuilder();
        teamList.stream().map(i->i.team).forEach(stringBuilders::append);
        return stringBuilders.reverse().toString();
    }

}

class Team{
    Character team;
    int[] vote= new int[3];

    public Team(Character team) {
        this.team = team;
    }
}

abstract class TeamComparator implements Comparator<Team>{
    Comparator<Team> tieBreaker;
    public TeamComparator(TeamComparator tieBreaker)
    {
        this.tieBreaker=tieBreaker;
    }
}

class FirstPositionComparator extends TeamComparator{

    public FirstPositionComparator(TeamComparator tieBreaker) {
        super(tieBreaker);
    }

    @Override
    public int compare(Team o1, Team o2) {
        if(o1.vote[0]==o2.vote[0])
            return tieBreaker.compare(o1,o2);
        else
        {
            return o1.vote[0]>o2.vote[0]?1:-1;
        }
    }
}
class SecondPositionComparator extends TeamComparator{

    public SecondPositionComparator(TeamComparator tieBreaker) {
        super(tieBreaker);
    }

    @Override
    public int compare(Team o1, Team o2) {
        if(o1.vote[1]==o2.vote[1])
            return tieBreaker.compare(o1,o2);
        else
        {
            return o1.vote[1]>o2.vote[1]?1:-1;
        }
    }
}
class ThirdPositionComparator extends TeamComparator{

    public ThirdPositionComparator(TeamComparator tieBreaker) {
        super(tieBreaker);
    }

    @Override
    public int compare(Team o1, Team o2) {
        if(o1.vote[2]==o2.vote[2])
            return tieBreaker.compare(o1,o2);
        else
        {
            return o1.vote[1]>o2.vote[1]?1:-1;
        }
    }
}
class StringComparator extends TeamComparator{

    public StringComparator(TeamComparator tieBreaker) {
        super(tieBreaker);
    }

    @Override
    public int compare(Team o1, Team o2) {
        return o1.team>o2.team?1:-1;
    }
}