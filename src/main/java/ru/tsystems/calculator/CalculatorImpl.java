package ru.tsystems.calculator;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class CalculatorImpl implements Calculator {

    private int start = -1;
    private int end = -1;

    private String exp;

    @Override
    public String evaluate(String expression) {

        String result = "";

        exp = expression;

        // TODO: Работа со скобками
        for(int i = 0; i < exp.length(); i++)
        {
            if((exp.charAt(i) == '(')&&( i > start ) ){//поиск открытия скобки
                start = i;
                end = -1;
                for(int j = start; j < exp.length(); j++){
                    if(exp.charAt(j) == ')'){//поиск закрытия скобки
                       end = j;
                       i = start;
                       break;
                    }

                }

                if (end == -1){
//                    throw new ArithmeticException("скобки в выражении не закрыты");
                    return null;
                }

                result = "";

                //Переписываем строку, где вместо выражения в скобках вставляем решение
                for (int j = 0; j < exp.length(); j++ ){
                    if ((j < start)||(j > end)){
                        result += exp.charAt(j);
                    }
                    if(j == end) {
                        result += solve(exp,start+1,end-1);
                    }
                }
                exp = result;
                i = 0;

            }



        }

//Если нет открывающейся скобки, но есть закрывающая то возвращаем null.
        for(int j = 0; j < exp.length(); j++) {
            if (exp.charAt(j) == ')') {//поиск закрытия скобки
//                throw new ArithmeticException("скобки в выражении не открыты");
                return null;
            }
        }

// Если нет открывающейся скобки, вычисляем оставшееся выражение
        // TODO: Установка формата вывода
        DecimalFormat df = new DecimalFormat("#.####", new DecimalFormatSymbols(Locale.ENGLISH));//формируем вывод по паттерну
        df.setRoundingMode(RoundingMode.CEILING);

        try{
            return  df.format(Float.parseFloat(solve(exp, 0, exp.length()-1)));

        }catch(NullPointerException e){
            return null;
        }

    }

    private String solve(String expression, int start, int end) {
        // TODO: Решение простого выражения
        String exp = expression.substring(start,end+1);
        int opInx = -1;//индекс оператора
        int nbrInx1 = -1;//индекс левого оператора
        int nbrInx2 = -1;//индекс правого

        //умножение деление
        for(int i = 0 ; i < exp.length(); i++){
            if ((exp.charAt(i) == '*')||(exp.charAt(i) == '/')){
                opInx = i;

                if((opInx == 0)||(opInx == exp.length()-1)){
//                    throw new ArithmeticException("выражение не закончено");
                    return null;
                }

                for(int j = opInx-1; j >= 0; j-- ){//поиск левого соседнего оператора или границы
                    if ((j == -1)||
                            (exp.charAt(j) == '*')||
                            (exp.charAt(j) == '/')||
                            (exp.charAt(j) == '+')||
                            (exp.charAt(j) == '-')
                            ){
                        nbrInx1 = j;
                        break;
                    }
                }

                for(int j = opInx+1; j <= exp.length(); j++ ){//поиск правого соседнего оператора или границы
                    if ((j == exp.length())||
                            (exp.charAt(j) == '*')||
                            (exp.charAt(j) == '/')||
                            (exp.charAt(j) == '+')||
                            (exp.charAt(j) == '-')
                            ){
                        nbrInx2 = j;
                        break;
                    }
                }


                if(exp.charAt(opInx) == '*'){//операция умножения

                    try{
                    exp = exp.substring(0,nbrInx1+1)+
                            (Float.parseFloat(exp.substring(nbrInx1+1,opInx))*
                                    Float.parseFloat(exp.substring(opInx+1,nbrInx2)))+
                            exp.substring(nbrInx2);
                    }catch(IllegalArgumentException e){
                        return null;
                    }

                }else{//операция деления

                    try{
                    exp = exp.substring(0,nbrInx1+1)+
                            (Float.parseFloat(exp.substring(nbrInx1+1,opInx))/
                                    Float.parseFloat(exp.substring(opInx+1,nbrInx2)))+
                            exp.substring(nbrInx2);
                    }catch(IllegalArgumentException e){
                        return null;
                    }
                }

                i = 0;

            }
        }

        //сложение вычитание
        for(int i = 0 ; i < exp.length(); i++){
            if ((exp.charAt(i) == '+')||(exp.charAt(i) == '-')){
                opInx = i;

                if((opInx == 0)||(opInx == exp.length()-1)){
//                    throw new ArithmeticException("выражение не закончено");
                    return null;
                }

                for(int j = opInx-1; j >= 0; j-- ){//поиск левого соседнего оператора или границы
                    if ((j == -1)||
                            (exp.charAt(j) == '*')||
                            (exp.charAt(j) == '/')||
                            (exp.charAt(j) == '+')||
                            (exp.charAt(j) == '-')
                            ){
                        nbrInx1 = j;
                        break;
                    }
                }

                for(int j = opInx+1; j <= exp.length(); j++ ){//поиск правого соседнего оператора или границы
                    if ((j == exp.length())||
                            (exp.charAt(j) == '*')||
                            (exp.charAt(j) == '/')||
                            (exp.charAt(j) == '+')||
                            (exp.charAt(j) == '-')
                            ){
                        nbrInx2 = j;
                        break;
                    }
                }


                if(exp.charAt(opInx) == '+'){ // операция сложения

                    try {
                        exp = exp.substring(0, nbrInx1 + 1) +
                                (Float.parseFloat(exp.substring(nbrInx1 + 1, opInx)) +
                                        Float.parseFloat(exp.substring(opInx + 1, nbrInx2))) +
                                exp.substring(nbrInx2);
                    }catch(IllegalArgumentException e){
                        return null;
                    }

                }else{//операция вычитания

                    try{
                    exp = exp.substring(0,nbrInx1+1)+
                            (Float.parseFloat(exp.substring(nbrInx1+1,opInx))-
                                    Float.parseFloat(exp.substring(opInx+1,nbrInx2)))+
                            exp.substring(nbrInx2);
                    }catch(IllegalArgumentException e){
                        return null;
                    }

                }

                i = 0;

            }
        }

        return exp;
    }
}
