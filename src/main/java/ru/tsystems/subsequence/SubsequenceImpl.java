package ru.tsystems.subsequence;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class SubsequenceImpl implements Subsequence {


    @Override
    public boolean find(List<Object> x, List<Object> y) {
        // TODO: Поиск элементов в правильной последовательности
        int start = -1;

        boolean flag = false;

        for(int i = 0; i < x.size(); i++){// берем ячейку  из списка X

            for(int j = start+1; j < y.size(); j++){//сравниваем с каждой оставвшейся ячейкой списка Y 
                try{
                    if(toString(x.get(i)).hashCode() == toString(y.get(j)).hashCode()){

                        flag = true;
                        start = j;//место откуда начнется поиск следующего элемента
                        break;
                    }
                } catch (IllegalAccessException e) {
                    System.out.println("проблема с рефлексией в методе toString");
                    e.printStackTrace();
                }
            }
            if(flag == false){
                return false;
            }else{
                flag = false;
            }

        }
        return true;

    }


    // TODO: Универсальное сравнение полей класса а не ссылок
    //Поскольку мы не уверены, что в параметры метода fine() будут вноситься 
    // списки в составе которых будут только примитивы, поэтому можно переопределить equals и hashcode,
    // но мы воспользуемся рефлексией и сделаем собсвенный метод toString
    // который будет выдавать строку со всеми
    // перечисленными параметрами класса. Это позволит получать один хэшкод 
    //для объектов с разными ссылками, но одинаковыми полями.
    private static String toString(Object object) throws IllegalAccessException {
        Class classOfObject = object.getClass(); //узнаем класс указанного объекта
        String result = "> ";

        Field[] fields = classOfObject.getDeclaredFields();//получаем массив полей оъекта
        for (Field field : fields) {
            field.setAccessible(true);
            result += field.getName() + " = ";
            if (field.getType().isPrimitive() == true) {//проверяем поле на примитив

                result += field.get(object);

            }else if(field.getType().isArray() == true){

                int length = Array.getLength(field.get(object));

                for (int j=0; j < length; j++) {
                    result += "[ "+ Array.get(field.get(object), j) + " ]";
                }
            //проверка на Collection и Map
            }else if(field.getType().getInterfaces().equals(List.class)){
                result += "[ "+ field.get(object)  + " ]";
            }else if(field.getType().getInterfaces().equals(Queue.class)){
                result += "[ "+ field.get(object)  + " ]";
            }else if(field.getType().getInterfaces().equals(Set.class)){
                result += "[ "+ field.get(object)  + " ]";
            }else if(field.getType().getInterfaces().equals(Map.class)){
                Map objMap = (Map)field.get(object);
                for (Object key : objMap.keySet()){

                    result += "[ Key: " + key + ", value: " + objMap.get(key)  + " ]";

                }

            }else{
                result += "[ "+ field.get(object).toString() + " ]";
            }

            result += "; ";
        }


        return result;
    }
}
