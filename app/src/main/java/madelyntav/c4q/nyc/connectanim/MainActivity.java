package madelyntav.c4q.nyc.connectanim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int player=0;
    int [] spots={2,2,2,2,2,2,2,2,2};
    LinearLayout winningLayout;
    int [] [] winningCombinations={{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    boolean gameIsActive=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        winningLayout=(LinearLayout) findViewById(R.id.winningLayout);
        winningLayout.setTranslationX(-1000f);

    }

    public void dropIn(View view) {
        if (gameIsActive) {
            ImageView counter = (ImageView) view;
            String tag = (String) counter.getTag();
            int num = Integer.parseInt(tag);
            if (spots[num] == 2) {
                counter.setTranslationY(-1000f);
                if (player == 0) {
                    counter.setImageResource(R.drawable.yellow);
                    spots[num] = 0;
                    checkIfWon();
                    player = 1;

                } else if (player == 1) {
                    counter.setImageResource(R.drawable.red);
                    spots[num] = 1;
                    checkIfWon();
                    player = 0;
                }
                counter.animate().translationYBy(1000f).rotation(360).setDuration(1000);

            } else
                Toast.makeText(this, "Please Choose A Spot That Hasn't Been Taken", Toast.LENGTH_SHORT).show();
        }
    }

    public void checkIfWon() {
        TextView winner = (TextView) findViewById(R.id.winnerText);
        for (int[] winningPositions : winningCombinations) {
            if (spots[winningPositions[0]] == spots[winningPositions[1]] &&
                    spots[winningPositions[1]] == spots[winningPositions[2]] && spots[winningPositions[0]] != 2) {
                String playerColor;
                if (spots[winningPositions[1]] == 1) {
                    playerColor = "Red";
                } else {
                    playerColor = "Yellow";
                }
                winningLayout.setVisibility(View.VISIBLE);
                winningLayout.animate().translationXBy(1000f).setDuration(200);
                winner.setText(playerColor + " Player Won!");
                gameIsActive = false;

            }
            else {
                boolean gameIsOver=true;

                    for (int i: spots) {

                        if (i == 2) gameIsOver = false;
                    }
                        if (gameIsOver) {
                            winningLayout.animate().translationXBy(1000f).setDuration(200);
                            winner.setText("Draw, Play Again!");
                        }
                        }
                    }
                }


    public void playAgain(View view){
        winningLayout.setTranslationX(-1000f);
        player=0;
        for(int i=0;i<9;i++){
            spots[i]=2;
        }
        gameIsActive=true;
        GridLayout gridLayout= (GridLayout) findViewById(R.id.grid);
        for (int i=0;i<gridLayout.getChildCount(); i++){

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
        }
    }




