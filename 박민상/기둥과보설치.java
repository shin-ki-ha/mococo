package programmers;

import java.util.*;

public class 기둥과보설치 {
    static Block[][] blocks;
    static int N;
    public static void main(String[] args) {

    }

    public static int[][] solution(int n, int[][] build_frame) {
        N = n;
        blocks = new Block[n][n+1];
        for(int i=0; i<n; i++){
            for(int j=0; j<n+1; j++){
                blocks[i][j] = new Block();
            }
        }

        List<Comp> lst = new LinkedList<>();

        for(int[] frame : build_frame){
            int x = frame[0];
            int y = frame[1];
            int a = frame[2];
            int b = frame[3];

            int r=0, c=0;
            if(a==0){ // 기둥
                r = Math.abs(y-n+1);
                c = x;
            }else{ // 보
                r = Math.abs(y-n);
                c = x;
            }

            if(b==1){ // build
                if(isPosBuild(r,c,a)){
                    if(a==0) blocks[r][c].left=1;
                    else blocks[r][c].top=1;
                    lst.add(new Comp(x,y,a));
                }
            }else{
                if(isPosDel(x,y,a)){
                    int delidx = -1;
                    for(int i=0; i<lst.size(); i++){
                        Comp curr = lst.get(i);
                        if(curr.x==x && curr.y==y && curr.a==a){
                            delidx = i;
                            break;
                        }
                    }
                    if(delidx!=-1) {
                        lst.remove(delidx);
                        if(a==0) blocks[r][c].left=0;
                        else blocks[r][c].top=0;
                    }
                }
            }
        }

        Collections.sort(lst);
        int[][] answer = new int[lst.size()][3];

        for(int i=0; i<lst.size(); i++){
            Comp curr = lst.get(i);

            answer[i][0] = curr.x;
            answer[i][1] = curr.y;
            answer[i][2] = curr.a;
        }

        return answer;
    }

    static boolean isPosBuild(int r, int c, int a){
        if(a==0){ // 기둥
            if(r==N-1) return true;
            if(r+1<N && blocks[r+1][c].top==1){
                return true;
            }
            if(r+1<N && c-1>=0 && blocks[r+1][c-1].top==1){
                return true;
            }
            if(r+1<N && blocks[r+1][c].left==1){
                return true;
            }
        }
        else{
            if(blocks[r][c].left==1) return true;
            if(c+1<N+1 && blocks[r][c+1].left==1) return true;
            if(c-1>=0 && c+1<N+1 && blocks[r][c-1].top==1 && blocks[r][c+1].top==1) return true;
        }

        return false;
    }

    static boolean isPosDel(int r, int c, int a){
        if(a==0){
            if(r-1>=0 && blocks[r-1][c].left==1){
                if(blocks[r][c].top!=1) return false;
                if(c-1>=0 && blocks[r][c-1].top!=1) return false;
            }

            if(blocks[r][c].top==1){
                if(c==0) return false;
                if(c-1>=0 && blocks[r][c-1].top!=1) return false;
                if(c+1<N+1 && (blocks[r][c+1].top!=1 && blocks[r][c+1].left!=1)) return false;
            }

            if(c-1>=0 && blocks[r][c-1].top==1){
                
            }

        }



        return true;
    }

    static class Comp implements Comparable<Comp>{
        int x, y, a;

        Comp(int x, int y, int a){
            this.x=x;
            this.y=y;
            this.a=a;
        }

        public String toString(){
            return this.x+" "+this.y+" "+this.a;
        }

        @Override
        public int compareTo(Comp o) {
            if(this.x != o.x) return Integer.compare(this.x, o.x);
            else if(this.y != o.y) return Integer.compare(this.y, o.y);
            else return Integer.compare(this.a, o.a);
        }
    }
    static class Block{
        int top, left;

        Block(){
            this.top=0;
            this.left=0;
        }
    }
}
