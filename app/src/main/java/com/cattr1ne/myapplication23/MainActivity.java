package com.cattr1ne.myapplication23;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    // создание полей
    private PaintingView paintingView; // создание поля кастомизированного View
    private ImageButton fabBroom; // поле кнопки нового рисунка

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // присваивание полям id
        paintingView = findViewById(R.id.painting); // присваивание полю View
        fabBroom = findViewById(R.id.fabChackmark); // присваивание полю разметки кнопки fabCharckmark

        // обработка нажатия кнопки
        fabBroom.setOnClickListener(listener);
    }

    // создание слушателя
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // исполняемый код при нажатии кнопок
            switch (view.getId()) {
                case R.id.fabChackmark:
                    // код для создания нового рисунка
                    AlertDialog.Builder broomDialog = new AlertDialog.Builder(MainActivity.this); // создание диалогового окна типа AlertDialog
                    broomDialog.setTitle("Новый рисунок"); // заголовок диалогового окна
                    broomDialog.setMessage("Создать новый рисунок (имеющийся будет удалён)?"); // сообщение диалога

                    broomDialog.setPositiveButton("Да", new DialogInterface.OnClickListener(){ // пункт выбора "да"
                        public void onClick(DialogInterface dialog, int which){
                            paintingView.broom(); // запуск нового рисунка
                            dialog.dismiss(); // закрыть диалог
                        }
                    });
                    broomDialog.setNegativeButton("Отмена", new DialogInterface.OnClickListener(){  // пункт выбора "нет"
                        public void onClick(DialogInterface dialog, int which){
                            dialog.cancel(); // выход из диалога
                        }
                    });
                    broomDialog.show(); // отображение на экране данного диалога
                    break;
            }
        }
    };
}