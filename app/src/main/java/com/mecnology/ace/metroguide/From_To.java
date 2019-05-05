package com.mecnology.ace.metroguide;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class From_To extends AppCompatActivity {

    ArrayList<String> Alist=new ArrayList<String>();
    ListView list;
    int [] postions=new int[2];
    boolean firstS,secS;
    String [] FL={"المرج الجديده","المرج","عزبه النخيل","عين شمس","المطريه","حلميه الزيتون","حدائق الزيتون","سرايه الابه","حمامات القبه","كوبرى القبه","منشيه الصدر","الدمرداش","غمره","الشهداء","عرابى","جمال عبدالناصر","السادات","سعد زغلول","السيده زينب","الملك الصالح","مارى جرجس","الزهراء","دار السلام","حدائق المعادى","المعادى","ثكنات المعادى","طره البلد","كوتسيكا","طره الاسمنت","المعصره","حدائق حلوان","وادى حوف","جامعه حلوان","عين حلوان","حلوان"};
    String [] SL={"شبرا الخيمه","الزراعه","المظلات","الخلفاوى","سانت تريزا","روض الفرج","مسره","الشهداء","العتبه","محمد نجيب","السادات","الاوبرا","الدقى","البحوث","جامعه القاهره","فيصل","الجيزة","ام المصريين","ساقيه مكى","المنيب"};

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from__to);


        list=(ListView)findViewById(R.id.route);
        Resources res=getResources();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.from_to, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        final Spinner from = (Spinner) findViewById(R.id.from);
        from.setAdapter(adapter);

        final Spinner to = (Spinner) findViewById(R.id.to);
        to.setAdapter(adapter);


        from.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String Fstring=from.getSelectedItem().toString();
                String Sstring=to.getSelectedItem().toString();
                if(ThirdLine(Fstring,Sstring))
                {

                }
                else
                {
                    refresh(Fstring,Sstring);
                    makearray(postions[0],postions[1],firstS,secS);
                }
                refreshlist();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        to.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String Fstring=from.getSelectedItem().toString();
                String Sstring=to.getSelectedItem().toString();
                if(ThirdLine(Fstring,Sstring))
                {

                }
                else
                {
                    refresh(Fstring,Sstring);
                    makearray(postions[0],postions[1],firstS,secS);
                }
                refreshlist();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
    }

    void refresh(String fchoise,String schoise)
    {
        firstS=false;
        secS=false;
        for(int i=0;i<FL.length;i++)
        {
            if(fchoise.equals(FL[i]))
                firstS=true;
            if(schoise.equals(FL[i]))
                secS=true;
        }
        if(firstS)
        {
            for(int i=0;i<FL.length;i++)
            {
                if(fchoise.equals(FL[i])) {
                    postions[0] = i;break;
                }
            }
        }
        else
        {
            for(int i=0;i<SL.length;i++)
            {
                if(fchoise.equals(SL[i])) {
                    postions[0] = i;break;
                }
            }
        }
        if(secS)
        {
            for(int i=0;i<FL.length;i++)
            {
                if(schoise.equals(FL[i])) {
                    postions[1] = i;break;
                }
            }
        }
        else
        {
            for(int i=0;i<SL.length;i++)
            {
                if(schoise.equals(SL[i])) {
                    postions[1] = i;break;
                }
            }
        }
    }

    void makearray(int fp,int sp,boolean fbool,boolean sbool)
    {
        Alist.clear();
        if(fbool&&sbool)
        {
            Alist.add(FL[fp]);
            searchF(fp,FL[sp]);
        }
        else if(!fbool && !sbool)
        {
            Alist.add(SL[fp]);
            searchS(fp,SL[sp]);
        }////////////////////////////////
        else if(fbool && !sbool)
        {
            Alist.add(FL[fp]);
            boolean thirteen;
            if(fp<=14)
            {
                thirteen=true;
                searchF(fp,FL[13]);
            }
            else
            {
                thirteen=false;
                searchF(fp,FL[16]);
            }
            if(thirteen)
            {
                searchS(7,SL[sp]);
            }
            else
            {
                searchS(10,SL[sp]);
            }
        }///////////last one
        else if(!fbool && sbool)
        {
            Alist.add(SL[fp]);
            boolean thirteen;
            if(fp<=8)
            {
                thirteen=true;
                searchS(fp,SL[7]);
            }
            else
            {
                thirteen=false;
                searchS(fp,SL[10]);
            }
            if(thirteen)
            {
                searchF(13,FL[sp]);
            }
            else
            {
                searchF(16,FL[sp]);
            }
        }

    }
    void searchF(int pos,String tmp)
    {
        if(FL[pos].equals(tmp))
            return;
        boolean right=false;
        for(int i=pos;i<FL.length;i++)
        {
            if(FL[i].equals(tmp))
                right=true;
        }
        if(right)
        {
            for(int i=pos;i<FL.length;i++)
            {
                Alist.add(FL[i]);
                if(FL[i].equals(tmp))
                    break;
            }
        }
        else
        {
            for(int i=pos;i>=0;i--)
            {
                Alist.add(FL[i]);
                if(FL[i].equals(tmp))
                    break;
            }
        }
    }

    void searchS(int pos,String tmp)
    {
        if(SL[pos].equals(tmp))
            return;
        boolean right=false;
        for(int i=pos;i<SL.length;i++)
        {
            if(SL[i].equals(tmp))
                right=true;
        }
        if(right)
        {
            for(int i=pos+1;i<SL.length;i++)
            {
                Alist.add(SL[i]);
                if(SL[i].equals(tmp))
                    break;
            }
        }
        else
        {
            for(int i=pos-1;i>=0;i--)
            {
                Alist.add(SL[i]);
                if(SL[i].equals(tmp))
                    break;
            }
        }
    }

    void searchT1( String tmp)
    {
        String [] TL={"الاهرام","كليه البنات","الاستاد","ارض المعارض","العباسيه","عبده باشا","الجيش","باب الشعريه","العتبه"};
        if(TL[6].equals(tmp))
            return;
        for(int i=8;i>=0;i--)
        {
            Alist.add(TL[i]);
            if(TL[i].equals(tmp))
                break;
        }

    }

    void searchT2(int tmp)
    {
        String [] TL={"الاهرام","كليه البنات","الاستاد","ارض المعارض","العباسيه","عبده باشا","الجيش","باب الشعريه","العتبه"};
        if(tmp==8)
            return;
        for(int i=tmp;i<=8;i++)
        {
            Alist.add(TL[i]);
        }

    }

    void refreshlist()
    {
        for (int i=0;i<Alist.size()-1;i++)
            if(Alist.get(i).equals(Alist.get(i+1)))
                Alist.remove(i);
        list.setAdapter(new Adapter2(this,Alist));
    }
    boolean ThirdLine(String FS,String SS){
        Alist.clear();
        Alist.add(FS);
        String [] TL={"الاهرام","كليه البنات","الاستاد","ارض المعارض","العباسيه","عبده باشا","الجيش","باب الشعريه","العتبه"};
        int pos1=0,pos2=0;
        boolean firstword=false,secword=false;
        for(int i=0;i<TL.length;i++)
        {
            if(FS.equals(TL[i])) {
                firstword = true;
                pos1=i;
            }
            if(SS.equals(TL[i]))
            {
                secword=true;
                pos2=i;
            }
        }
        if(!firstword && !secword)
            return false;
        if(firstword && secword)
        {
            if(pos1>pos2)
            {
                for (int i=pos1;i>=pos2;i--)
                    Alist.add(TL[i]);
            }
            else
            {
                for (int i=pos1;i<=pos2;i++)
                    Alist.add(TL[i]);
            }
        }
        else if(firstword && !secword)
        {
            boolean first=firstorsecond(SS);
            if(first)
            {
                int pos=0;
                for(int i=0;i<TL.length;i++)
                {
                    if(TL[i].equals(FS))
                    {
                        pos=i;
                        break;
                    }
                }
                searchT2(pos);
                searchS(8,SL[7]);
                searchF(13,SS);
            }
            else
            {
                int pos=0;
                for(int i=0;i<TL.length;i++)
                {
                    if(TL[i].equals(FS))
                    {
                        pos=i;
                        break;
                    }
                }
                searchT2(pos);
                searchS(8,SS);
            }
        }
        else
        {
            boolean first=firstorsecond(FS);
            if(first)
            {
                int pos=0;
                for(int i=0;i<FL.length;i++)
                {
                    if(FL[i].equals(FS))
                    {
                        pos=i;
                        break;
                    }
                }
                searchF(pos,FL[13]);
                searchS(7,SL[8]);
                searchT1(SS);
            }
            else
            {
                int pos=0;
                for(int i=0;i<SL.length;i++)
                {
                    if(SL[i].equals(FS))
                    {
                        pos=i;
                        break;
                    }
                }
                searchS(pos,SL[7]);
                searchT1(SS);
            }
        }
        return true;
    }
    boolean firstorsecond(String tmp)
    {
        boolean first=false;
        for(int i=0;i<FL.length;i++)
        {
            if(tmp.equals(FL[i]))
                first=true;
        }
        return first;
    }
}
