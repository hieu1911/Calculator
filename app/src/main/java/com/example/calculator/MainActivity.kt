package com.example.calculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var numberOne = 0;
    private var numberSecond = 0;
    private lateinit var operator: Operator;
    private var hasCal = false;

    private lateinit var textView: TextView;
    private lateinit var prevTextView: TextView;

    @SuppressLint("SetTextI18n", "CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView);
        prevTextView = findViewById(R.id.prevTextView);

        val button0: Button = findViewById(R.id._0);
        val button1: Button = findViewById(R.id._1);
        val button2: Button = findViewById(R.id._2);
        val button3: Button = findViewById(R.id._3);
        val button4: Button = findViewById(R.id._4);
        val button5: Button = findViewById(R.id._5);
        val button6: Button = findViewById(R.id._6);
        val button7: Button = findViewById(R.id._7);
        val button8: Button = findViewById(R.id._8);
        val button9: Button = findViewById(R.id._9);
        val buttonCE: Button = findViewById(R.id.ce);
        val buttonC: Button = findViewById(R.id.c);
        val buttonBS: Button = findViewById(R.id.bs);
        val buttonDiv: Button = findViewById(R.id.div);
        val buttonMul: Button = findViewById(R.id.mul);
        val buttonMinus: Button = findViewById(R.id.minus);
        val buttonAdd: Button = findViewById(R.id.add);
        val buttonMore: Button = findViewById(R.id.more);
        val buttonDot: Button = findViewById(R.id.dot);
        val buttonEqual: Button = findViewById(R.id.equal);

        button0.setOnClickListener {
            handleClickButtonNumber(0)
        }

        button1.setOnClickListener {
            handleClickButtonNumber(1)
        }

        button2.setOnClickListener {
            handleClickButtonNumber(2)
        }

        button3.setOnClickListener {
            handleClickButtonNumber(3)
        }

        button4.setOnClickListener {
            handleClickButtonNumber(4)
        }

        button5.setOnClickListener {
            handleClickButtonNumber(5)
        }

        button6.setOnClickListener {
            handleClickButtonNumber(6)
        }

        button7.setOnClickListener {
            handleClickButtonNumber(7)
        }

        button8.setOnClickListener {
            handleClickButtonNumber(8)
        }

        button9.setOnClickListener {
            handleClickButtonNumber(9)
        }

        buttonCE.setOnClickListener {
            numberOne = 0;
            textView.text = numberOne.toString();
            prevTextView.text = "";
        }

        buttonC.setOnClickListener {
            if (!hasCal && numberSecond > 0) {
                prevTextView.text = "";
                numberOne = numberSecond;
                textView.text = numberOne.toString();
                numberSecond = 0;
            }
        }

        buttonBS.setOnClickListener {
            if (!hasCal) {
                numberOne /= 10;
                textView.text = numberOne.toString();
            }
        }

        buttonAdd.setOnClickListener {
            operator = Operator.ADD;
            setPrevTextView();
        }

        buttonMinus.setOnClickListener {
            operator = Operator.MINUS;
            setPrevTextView();
        }

        buttonMul.setOnClickListener {
            operator = Operator.MUL;
            setPrevTextView();
        }

        buttonDiv.setOnClickListener {
            operator = Operator.DIV;
            setPrevTextView();
        }

        buttonEqual.setOnClickListener {
            if (!hasCal) {
                hasCal = true;  var result = 0;
                when (operator) {
                    Operator.ADD -> result = numberSecond + numberOne;
                    Operator.MINUS -> result = numberSecond - numberOne;
                    Operator.MUL -> result = numberSecond * numberOne;
                    Operator.DIV -> result = numberSecond / numberOne;
                    else -> {}
                }

                prevTextView.text = "${prevTextView.text}$numberOne = ";
                textView.text = result.toString();
                numberOne = result;

            }
        }
    }

    /**
     * Hàm xử lý sự kiện khi bấm vào nút số từ 0 đến 9
     * @author DVHIEU (20/10/2023)
     */
    private fun handleClickButtonNumber(value: Int) {
        if (hasCal) {
            hasCal = false;
            numberOne = 0;
            prevTextView.text = "";
        }
        numberOne = numberOne * 10 + value;
        textView.text = numberOne.toString();
    }

    /**
     * Hàm gán lại giá trị cho TextView hiển thị biểu thức tính toán
     * @author DVHIEU (20/10/2023)
     */
    private fun setPrevTextView() {
        hasCal = false;
        numberSecond = numberOne;
        numberOne = 0;
        textView.text = numberOne.toString();
        var prevText: String = numberSecond.toString();

        when (operator) {
            Operator.ADD -> prevText += " + ";
            Operator.MINUS -> prevText += " - ";
            Operator.MUL -> prevText += " * ";
            Operator.DIV -> prevText += " / ";
            else -> {}
        }

        prevTextView.text = prevText;
    }
}