package com.mecnology.ace.metroguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class List_of_Metro_Stations extends AppCompatActivity {


    String [] FL={"المرج الجديده","المرج","عزبه النخيل","عين شمس","المطريه","حلميه الزيتون","حدائق الزيتون","سرايه الابه","حمامات القبه","كوبرى القبه","منشيه الصدر","الدمرداش","غمره","الشهدا","عرابى","جمال عبدالناصر","السادات","سعد زغلول","السيده زينب","الملك الصالح","مارى جرجس","الزهراء","دار السلام","حدائق المعادى","المعادى","ثكنات المعادى","طره البلد","كوتسيكا","طره الاسمنت","المعصره","حدائق حلوان","وادى حوف","جامعه حلوان","عين حلوان","حلوان"};
    String [] SL={"شبرا الخيمه","الزراعه","المظلات","الخلفاوى","سانت تريزا","روض الفرج","مسره","الشهداء","العتبه","محمد نجيب","السادات","الاوبرا","الدقى","البحوث","جامعه القاهره","فيصل","الجيزة","ام المصريين","ساقيه مكى","المنيب"};
    String [] TL={"الاهرام","كليه البنات","الاستاد","ارض المعارض","العباسيه","عبده باشا","الجيش","باب الشعريه","العتبه"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of__metro__stations);
        ListView list=(ListView)findViewById(R.id.metrolist);

        Intent i=getIntent();
        String LINE=i.getStringExtra("LINE");
        if(LINE.equals("FL"))
        {
            list.setAdapter(new Adapter(this,FL));
        }
        else if(LINE.equals("SL"))
        {
            list.setAdapter(new Adapter(this,SL));
        }
        else
        {
            list.setAdapter(new Adapter(this,TL));
        }
    }
}
