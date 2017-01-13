package com.javarush.test.level23.lesson13.big01;

import java.util.ArrayList;

/**
 * Класс змея
 */
public class Snake
{
    //Направление движения змеи
    private SnakeDirection direction;
    //Состояние - жива змея или нет.
    private boolean isAlive = true;
    //Список кусочков змеи.
    private ArrayList<SnakeSection> sections = new ArrayList<SnakeSection>();

    public Snake(int x, int y)
    {
        sections = new ArrayList<SnakeSection>();
        sections.add(new SnakeSection(x, y));
        isAlive = true;
    }

    public boolean isAlive()
    {
        return isAlive;
    }

    public int getX()
    {
        return sections.get(0).getX();
    }

    public int getY()
    {
        return sections.get(0).getY();
    }

    public SnakeDirection getDirection()
    {
        return direction;
    }

    public void setDirection(SnakeDirection direction)
    {
        this.direction = direction;
    }

    public ArrayList<SnakeSection> getSections()
    {
        return sections;
    }

    /**
     * Метод перемещает змею на один ход.
     * Направление перемещения задано переменной direction.
     */
    public void move()
    {
        if (!isAlive) return;

        if (direction == SnakeDirection.UP)
            move(0, -1);
        else if (direction == SnakeDirection.RIGHT)
            move(1, 0);
        else if (direction == SnakeDirection.DOWN)
            move(0, 1);
        else if (direction == SnakeDirection.LEFT)
            move(-1, 0);
    }

    /**
     * Метод перемещает змею в соседнюю клетку.
     * Кординаты клетки заданы относительно текущей головы с помощью переменных (dx, dy).
     */
    private void move(int dx, int dy)
    {
        SnakeSection head = new SnakeSection(sections.get(0).getX() + dx, sections.get(0).getY() + dy);//Создаем новую голову - новый "кусочек змеи".
        checkBorders(head);
        if (!isAlive) return; //Проверяем - не вылезла ли голова за границу комнаты
        checkBody(head);
        if (!isAlive) return; //Проверяем - не пересекает ли змея  саму себя
        if (isAlive)
        {//Проверяем - не съела ли змея мышь.
            if (head.getX() == Room.game.getMouse().getX() && head.getY() == Room.game.getMouse().getY())
            {
                Room.game.eatMouse();
                sections.add(0, new SnakeSection(dx, dy));

            } else
            {
                sections.add(0, new SnakeSection(dx, dy));
                sections.remove(sections.size() - 1);
            }
        }
        //Двигаем змею.
    }

    /**
     * Метод проверяет - находится ли новая голова в пределах комнаты
     */
    private void checkBorders(SnakeSection head)
    {
        if (head.getX() < 0 || head.getX() > Room.game.getHeight() || head.getY() < 0 || head.getY() > Room.game.getWidth())
            isAlive = false;
    }

    /**
     * Метод проверяет - не совпадает ли голова с каким-нибудь участком тела змеи.
     */
    private void checkBody(SnakeSection head)
    {
        if (sections.contains(head)) isAlive = false;
    }
}