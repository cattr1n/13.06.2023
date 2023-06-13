package com.cattr1ne.myapplication23;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class PaintingView extends View {

    // создание полей
    private Path path; // поле рисования контура
    private Paint paint; // поле задания стиля рисования
    private final int PAINT_COLOR_DEFAULT = 0xFFFFB772; // цвет по умолчанию (в шеснадцатиричном виде: 0x Прозрачность Красный Зелёный Голубой)
    private Canvas canvas; // поле холста для рисования
    private Bitmap bitmap; // поле для отрисованного изображения

    // конструктор кастомизированого View
    public PaintingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        // настройки области рисования
        path = new Path(); // создание объекта рисования контура

        paint = new Paint(); // создание объекта стиля рисования
        paint.setAntiAlias(true); // сглаживание краёв
        paint.setStyle(Paint.Style.STROKE); // определение стиля кисти
        paint.setStrokeCap(Paint.Cap.ROUND); // определение вида концов отрисовываемых линий
        paint.setStrokeJoin(Paint.Join.ROUND); // стиль объединения отрисовываемых линий
        paint.setColor(PAINT_COLOR_DEFAULT); // установка цвета линии
        paint.setStrokeWidth(25); // толщина отрисовываемой линии
    }

    // определение размера View (новая ширина, новая длина, старая ширина, старая длина)
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // создание пустого изображение с заданной шириной, высотой и цветовой схемой (альфа, красный, зеленый, голубой)
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        // создание холста по созданному изображению
        canvas = new Canvas(bitmap);
    }

    // определение свойств холста
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // задание рисунка на холсте и демонстрация отрисовки линий
        canvas.drawBitmap(bitmap, 0, 0, paint); // запись на холсте отрисованных линий
        canvas.drawPath(path, paint); // демонстрация отрисовки линий
    }

    // определение слушателя событий касания экрана
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        // извлечение координат касания экрана
        float x = motionEvent.getX();
        float y = motionEvent.getY();

        // обработка событий касания экрана
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN: // прикосновение к экрану
                path.moveTo(x, y); // определение начала контура
                path.lineTo(x, y); // задание линии контура
                break;
            case MotionEvent.ACTION_MOVE: // движение по экрану
                path.lineTo(x, y); // задание линии контура
                break;
            case MotionEvent.ACTION_UP: // отпускание экрана
            case MotionEvent.ACTION_CANCEL: // внутрений сбой (аналогичен ACTION_UP)
                canvas.drawPath(path, paint); // отрисовка на хосте сгенерированных данных
                path.reset(); // прерывание линии
                break;
        }
        invalidate(); // обновление View
        return true;
    }

    // метод очистки экрана
    public void broom() {
        // очистка экрана и заполнение его белым цветом
        canvas.drawColor(0xFFFFFFFF, PorterDuff.Mode.CLEAR);
        // обновление представления View
        invalidate();
    }
}