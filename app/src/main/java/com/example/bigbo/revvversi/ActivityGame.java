package com.example.bigbo.revvversi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityGame extends AppCompatActivity{

    //число кликов
    public static int clickedTimes = 0;

    //ячейки и их значения
    private ImageView[][] cells;
    private int[][] values = new int[8][8];

    //размер поля и столбцы/строки
    private int boardSize = 8;
    private int j, i;

    //счет
    private int scoreBlack;
    private int scoreWhite;

    //компоненты приложения
    private TableLayout tablelayout;
    private TextView b_counter;
    private TextView w_counter;
    private TextView turn;
    private TextView back;

    //направление, в котором ходит игрок
    public static boolean EAST = false;
    public static boolean WEST = false;
    public static boolean NORTH = false;
    public static boolean SOUTH = false;
    public static boolean NORTHEAST = false;
    public static boolean SOUTHEAST = false;
    public static boolean NORTHWEST = false;
    public static boolean SOUTHWEST = false;

    //объект класса, методы которого отвечают за переворот фишек
    private final ValuesFlips flip = new ValuesFlips();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameactivity);

        if (ActivityOptions.WALL1)
            getWindow().setBackgroundDrawableResource(R.drawable.truepicture);
        if (ActivityOptions.WALL2)
            getWindow().setBackgroundDrawableResource(R.drawable.anothertruepicture);
        if (ActivityOptions.WALL3) getWindow().setBackgroundDrawableResource(R.drawable.withoutja);

        tablelayout = findViewById(R.id.main_l);
        b_counter = findViewById(R.id.textView4);
        w_counter = findViewById(R.id.textView5);
        turn = findViewById(R.id.textView6);
        back = findViewById(R.id.backGameScreen);
        buildGameField();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("scoreB", scoreBlack);
        outState.putInt("scoreW", scoreWhite);
        outState.putString("turn", (String) turn.getText());
        outState.putInt("clicked", clickedTimes);
        outState.putSerializable("v", values);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        scoreBlack = savedInstanceState.getInt("scoreB");
        scoreWhite = savedInstanceState.getInt("scoreW");
        clickedTimes = savedInstanceState.getInt("clicked");
        values = (int[][]) savedInstanceState.getSerializable("v");

        b_counter.setText(String.valueOf(scoreBlack));
        w_counter.setText(String.valueOf(scoreWhite));
        turn.setText(savedInstanceState.getString("turn"));

        for (int m = 0; m < boardSize; m++) {
            for (int n = 0; n < boardSize; n++) {
                if (values[n][m] == 1) {
                    cells[n][m].setImageResource(R.drawable.blackcell);
                } else if (values[n][m] == 2) {
                    cells[n][m].setImageResource(R.drawable.whitecell);
                }
            }
        }
    }

    //создание ирового поля
    @SuppressLint("SetTextI18n")
    private void buildGameField() {

        cells = new ImageView[boardSize][boardSize];
        turn.setText(getString(R.string.black_s_turn));

        for (i = 0; i < boardSize; i++) {
            TableRow row = new TableRow(this);
            for (j = 0; j < boardSize; j++) {
                cells[j][i] = new ImageView(this);
                row.addView(cells[j][i], new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                cells[j][i].setImageResource(R.drawable.nonecell);
                final int vRow = j;
                final int vCol = i;
                clickOnCell(vRow, vCol);
            }
            tablelayout.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
        }

        //стартовые значения
        values[3][4] = values[4][3] = 1;
        values[4][4] = values[3][3] = 2;
        cells[3][4].setImageResource(R.drawable.blackcell);
        cells[4][3].setImageResource(R.drawable.blackcell);
        cells[4][4].setImageResource(R.drawable.whitecell);
        cells[3][3].setImageResource(R.drawable.whitecell);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoMenu = new Intent(ActivityGame.this, ActivityMenu.class);
                gotoMenu.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(gotoMenu);

                tablelayout.removeAllViews();
                for (i = 0; i < boardSize; i++) {
                    for (j = 0; j < boardSize; j++) {
                        cells[j][i] = null;
                    }
                }
                b_counter.setText(getString(R.string.b_counter));
                w_counter.setText(getString(R.string.w_score));
                clickedTimes = 0;
                values = new int[8][8];
                buildGameField();
            }
        });
    }

    //слуштаель ячейки
    private void clickOnCell(final int row, final int col) {
        cells[j][i].setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                scoreBlack = 0;
                scoreWhite = 0;

                //если ячейка пустая
                if (values[row][col] == 0) {
                    flip.valuesNorth(values, row, col, false);
                    flip.valuesSouth(values, boardSize, row, col, false);
                    flip.valuesEast(values, boardSize, row, col, false);
                    flip.valuesWest(values, row, col, false);
                    flip.valuesSouthWest(values, boardSize, row, col, false);
                    flip.valuesNorthWest(values, row, col, false);
                    flip.valuesSouthEast(values, boardSize, row, col, false);
                    flip.valuesNorthEast(values, boardSize, row, col, false);

                    //проверка на то, можно ли перевернуть ряд фишек
                    boolean isFlippable = EAST || SOUTH || WEST || NORTH || NORTHEAST ||
                            SOUTHEAST || SOUTHWEST || NORTHWEST;

                    //при возможности хода - заполнение ячейки соответствующим значением
                    //"1" - черная фишка, "2" - белая фишка
                    if (values[row][col] == 0 && isFlippable) {
                        ++clickedTimes;
                        if (clickedTimes % 2 == 1) {
                            values[row][col] = 1;
                        } else {
                            values[row][col] = 2;
                        }
                    }

                    //отрисовка поля с учетом новых значений
                    for (int m = 0; m < boardSize; m++) {
                        for (int n = 0; n < boardSize; n++) {
                            if (values[n][m] == 1) {
                                cells[n][m].setImageResource(R.drawable.blackcell);
                            } else if (values[n][m] == 2) {
                                cells[n][m].setImageResource(R.drawable.whitecell);
                            }
                        }
                    }

                    //обновление счета
                    counter(values);
                    b_counter.setText(String.valueOf(scoreBlack));
                    w_counter.setText(String.valueOf(scoreWhite));
                }

                //обновление следующего хода
                if (clickedTimes % 2 == 1) turn.setText(getString(R.string.white_s_turn));
                else turn.setText(getString(R.string.black_s_turn));

                //проверка на возможность хода и полное заполнение игрвого поля
                boolean noMove = true;
                boolean noSpace = true;

                for (int i = 0; i < boardSize; i++) {
                    for (int j = 0; j < boardSize; j++) {
                        if (values[j][i] == 0) {
                            noMove = !flip.valuesNorth(values, j, i, true) &&
                                    !flip.valuesSouth(values, boardSize, j, i, true) &&
                                    !flip.valuesEast(values, boardSize, j, i, true) &&
                                    !flip.valuesWest(values, j, i, true) &&
                                    !flip.valuesSouthWest(values, boardSize, j, i, true) &&
                                    !flip.valuesNorthWest(values, j, i, true) &&
                                    !flip.valuesSouthEast(values, boardSize, j, i, true) &&
                                    !flip.valuesNorthEast(values, boardSize, j, i, true);
                            noSpace = false;

                            if (!noMove) break;
                        }
                    }
                    if (!noMove) break;
                }

                //сообщения о невозможности хода или полном заполнении поля
                if (noSpace) {
                    if (scoreBlack > scoreWhite) {
                        Toast toast = Toast.makeText(ActivityGame.this, getString(R.string.b_win_message), Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    } else {
                        Toast toast = Toast.makeText(ActivityGame.this, getString(R.string.w_win_message), Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                } else if (noMove) {
                    Toast toast = Toast.makeText(ActivityGame.this, getString(R.string.no_move_message),
                            Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    clickedTimes++;
                    if (clickedTimes % 2 == 1) turn.setText(getString(R.string.white_s_turn));
                    else turn.setText(getString(R.string.black_s_turn));
                }
            }
        });
    }

    //счетчик
    public int[] counter(int[][] array) {
        for (int[] items : array) {
            for (int item : items) {
                if (item == 1) {
                    scoreBlack++;
                } else if (item == 2) {
                    scoreWhite++;
                }
            }
        }

        return new int[]{scoreBlack, scoreWhite};
    }
}
