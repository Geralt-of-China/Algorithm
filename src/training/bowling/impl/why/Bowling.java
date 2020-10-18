package training.bowling.impl.why;

public class Bowling {

    //测试函数
    public static void main(String args[]) {
        int[][][] testCase = {
                {{10}, {10}, {10}, {10}, {10}, {10}, {10}, {10}, {10}, {10}, {10}, {10}},
                {{0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}},
                {{10}, {10}, {10}, {10}, {10}, {10}, {10}, {10}, {10}, {10}, {3, 7}},
                {{10}, {10}, {10}, {10}, {10}, {10}, {10}, {10}, {10}, {1, 9}, {10}},
                {{5, 5}, {5, 5}, {5, 5}, {5, 5}, {5, 5}, {5, 5}, {5, 5}, {5, 5}, {5, 5}, {5, 5}, {5}},
                {{-1, -4}, {11, -1}},
                {{10}, {10}, {10}, {10}, {10}, {10}, {10}, {10}, {10}, {10}, {3, 8}},
                {{10}, {10}, {10}, {10}, {10}, {10}, {10}, {10}, {10}, {10}, {10}, {7}},

        };
        for (int[][] temp :
                testCase) {
            try {
                Bowling a = new Bowling(temp);
                System.out.println(a.getTotal());
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
    }

    //condition constant
    public enum Status {
        MISS,
        SPARE,
        STRIKE
    }

    //max score for each throw
    private static final int maxSroce = 10;
    //total round number
    private static final int round = 10;

    private int[][] turns;
    //score for each round
    private int[] score = new int[round];
    //status for each round
    private Status[] situation = new Status[round];


    public Bowling(int[][] turns) throws Exception {
        this.turns = turns;
        check(turns);
        calculate(turns);
    }

    /**
     * calculate score for each round
     *
     * @param turns
     * @throws Exception if the input is illegal,this method will throw an exception
     */
    private void calculate(int[][] turns) throws Exception {
        int score = 0;
        for (int i = 0; i < round; i++) {
            int[] t = turns[i];
            if (t[0] == maxSroce) {
                score += (maxSroce + getNext(turns, i, 2));
                situation[i] = Status.STRIKE;
            } else if (t[0] + t[1] == maxSroce) {
                score += (maxSroce + getNext(turns, i, 1));
                situation[i] = Status.SPARE;
            } else {
                score += (t[0] + t[1]);
                situation[i] = Status.MISS;
            }
            this.score[i] = score;
        }
    }

    /**
     * get score for ith round
     *
     * @param ith
     * @return
     */
    public int getIthSroce(int ith) {
        if (ith <= 0 || ith > round) throw new IllegalArgumentException();
        return score[ith - 1];
    }

    /**
     * get status for each round
     *
     * @param ith
     * @return
     */
    public Status getIthStatus(int ith) {
        if (ith <= 0 || ith > round) throw new IllegalArgumentException();
        return situation[ith - 1];
    }

    /**
     * get total score
     *
     * @return
     */
    public int getTotal() {
        return score[round - 1];
    }

    /**
     * @param turns  the information about bowling
     * @param index
     * @param number
     * @return get score of next @number throw after @index round
     */
    private int getNext(int[][] turns, int index, int number) {
        if (number == 1) return turns[index + 1][0];
        else {
            if (turns[index + 1].length == 2) {
                return turns[index + 1][0] + turns[index + 1][1];
            } else {
                return turns[index + 1][0] + turns[index + 2][0];
            }
        }
    }

    /**
     * check whether turns is legal
     *
     * @param turns
     * @throws Exception
     */
    private void check(int[][] turns) throws Exception {
        if (turns.length < 10) throw new Exception("at least 10 round");
        for (int i = 0; i < 10; i++) {
            int[] temp = turns[i];
            if (temp.length < 1) throw new Exception("at least one throw for each round");
            if (temp[0] < 0 || temp[0] > 10) throw new Exception("illegal score");
            if (temp[0] == 10) {
                if (temp.length != 1) throw new Exception("if you strike,you can only throw once");
            } else {
                if (temp.length != 2) throw new Exception("if you don't strike,you have to throw twice");
                if (temp[1] + temp[0] > 10) throw new Exception("illegal score");
            }
        }

        if (turns[9].length == 1) {
            if (turns.length == 11) {
                if (turns[10].length != 2 || turns[10][0] < 0 || turns[10][0] > 10 || turns[10][0] + turns[10][1] > 10) {
                    throw new Exception("last tow rounds are illegal");
                }
            } else if (turns.length == 12) {
                if (turns[10].length != 1 || turns[10][0] != 10 || turns[11].length != 1 || turns[11][0] < 0 || turns[11][0] > 10)
                    throw new Exception("last tow rounds are illegal");
            } else throw new Exception("last tow rounds are illegal");

        } else if (turns[9].length == 2 && turns[9][0] + turns[9][1] == 10) {
            if (turns.length != 11 || turns[10].length != 1 || turns[10][0] < 0 || turns[10][0] > 10)
                throw new Exception("last tow rounds are illegal");
        }
    }

}
