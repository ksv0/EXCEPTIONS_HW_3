package ksv;

import ksv.exceptions.IncorrectlyDataException;
import ksv.service.AppService;
import ksv.service.ServiceInterface;
import ksv.view.ConsoleView;
import ksv.view.View;

public class App {
    View view = new ConsoleView();
    ServiceInterface service = new AppService(view);

    public void run() {

        boolean flag = true;
        String str;
        while (flag) {
            view.set("""
                    Введите данные в формате(через пробел):
                    Фамилия Имя Отчество датарождения номертелефона пол
                    Форматы данных:
                     * фамилия, имя, отчество - строки
                     * датарождения - строка формата dd.mm.yyyy
                     * номертелефона - целое беззнаковое число без форматирования
                     * пол - символ латиницей f или m.
                     1 или exit или qqq - выход
                    """);
            try {
                str = view.get();
                if (str.equals("1") || str.equals("exit") || str.equals("qqq")) {
                    flag = false;
                    continue;
                }
                service.save(service.parse(str));
            } catch (IncorrectlyDataException e) {
                view.set(e.getMessage());

            }
        }
    }


}
