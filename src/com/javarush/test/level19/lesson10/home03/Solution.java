package com.javarush.test.level19.lesson10.home03;

import java.io.BufferedReader;
import java.io.FileReader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* Хуан Хуанович
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя день месяц год
где [имя] - может состоять из нескольких слов, разделенных пробелами, и имеет тип String
[день] - int, [месяц] - int, [год] - int
данные разделены пробелами

Заполнить список PEOPLE импользуя данные из файла
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Иванов Иван Иванович 31 12 1987
Вася 15 5 2013
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        String[] buff;
        StringBuilder stringBuilder;
        StringBuilder stringBuilder1;
        Date date;
        while (reader.ready())
        {
            buff = reader.readLine().split(" ");
            stringBuilder = new StringBuilder(buff[0]);
            if (buff.length == 5) stringBuilder.append(" ").append(buff[1]);
            if (buff.length == 6) stringBuilder.append(" ").append(buff[1]).append(" ").append(buff[2]);
            stringBuilder1 = new StringBuilder();
            stringBuilder1.append(buff[buff.length-3]).append("-").append(buff[buff.length-2]).append("-").append(buff[buff.length-1]);
            date = new SimpleDateFormat("dd-MM-yyyy").parse(stringBuilder1.toString());
            PEOPLE.add(new Person(stringBuilder.toString(), date));
        }
        reader.close();
        for (Person person : PEOPLE)
        {
            System.out.println(person.getName() + person.getBirthday());
        }
    }

}
