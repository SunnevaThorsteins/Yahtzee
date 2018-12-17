/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.yatzee.vinnsla;

import java.util.Random;

/**
 *
 * @author Sunneva
 */


public class YatzeeImp implements Yatzee {
    
    private String nafnLeikmanns1; //Inniheldur nafn spilara 1
    private String nafnLeikmanns2; //Inniheldur naf spilara 2
    public final int[] stigin1 = new int[14]; //Heldur utan um stig spilara 1
    public final int[] stigin2 = new int[14]; //Heldur utan um stig spilara 1
    private int summa1=0;//Summa fyrir spilara 1
    private int summa2=0;//Summa fyrir spilara 2
    public int nuverandi = 1; //hvaða leikmaður á að gera
    public int einfaldir1 = 0; //Hversu marga einfalda er búið að reikna
    public int einfaldir2 = 0; //Hversu marga einfalda er búið að reikna
    public boolean buinBonus1 = false; //Hvort bónusinn fyrir spilara 1 sé búinn
    public boolean buinBonus2 = false; //Hvort bónusinn fyrir spilara 3 sé búinn
    private int vin = 0; //Hversu margar umferðir eru búnar
    public boolean mogulegStig; //Hvort verið sé að sýna möguleg stig eða ekki

    @Override
    public String getNafnLeikmanns1() {
        return this.nafnLeikmanns1;
    }
    
    @Override
    public String getNafnLeikmanns2() {
        return this.nafnLeikmanns2;
    }

    @Override
    public int getSumma1() {
        return this.summa1;
        
    }
    
    @Override
    public int getSumma2() {
        return this.summa2;
        
    }

    @Override
    public int reiknaEinfalda(int i, Teningar teningar) {
        if(nuverandi == 1){
            stigin1[i] = 0;
            for(int t:teningar.getTeningar()) {
                if (t == (i+1)) {
                    stigin1[i] = stigin1[i] + i+1;
                }
            }
            if(!mogulegStig)
                einfaldir1++;
            return stigin1[i]; 
        } else {
            stigin2[i] = 0;
            for(int t:teningar.getTeningar()) {
                if (t == (i+1)) {
                    stigin2[i] = stigin2[i] + i+1;
                }
            }
            if(!mogulegStig)
                einfaldir2++;
            return stigin2[i];
        }
        
    }
    
    @Override
    public int reiknaSensinn(int n, Teningar teningar) {
        return reiknaAllaTeninga(n, teningar);
    }
    
    private int reiknaAllaTeninga(int num, Teningar teningar){
        if(nuverandi == 1){
            stigin1[num] = 0;
            for(int t : teningar.getTeningar()){
                stigin1[num] = stigin1[num]+t;
            }
            return stigin1[num];
        } else {
            stigin2[num] = 0;
            for(int t : teningar.getTeningar()){
                stigin2[num] = stigin2[num]+t;
            }
            return stigin2[num];
        }
    }

    @Override
    public int reiknaSumma() {
        vin++;
        if(nuverandi == 1){
            this.summa1 = 0;
        for(int i = 0; i < 14; i++){
            this.summa1 += stigin1[i];
        }
        return this.summa1;
        } else {
            this.summa2 = 0;
            for(int i = 0; i < 14; i++){
                this.summa2 += stigin2[i];
            }
            return this.summa2;
        }
    }

    @Override
    public void setNafnLeikmanns1(String nafnLeikmanns) {
        this.nafnLeikmanns1 = nafnLeikmanns;
    }
    
    @Override
    public void setNafnLeikmanns2(String nafnLeikmanns) {
        this.nafnLeikmanns2 = nafnLeikmanns;
    }
    

    @Override
    public int reiknaEins(int n, Teningar teningar) {
        int eins = 0;
        for(int t : teningar.getTeningar()){
            for(int e : teningar.getTeningar()){
                if(t == e){
                    eins++;
                }
            }
            if(eins >= n-3){
                return reiknaAllaTeninga(n, teningar);
            }
            eins = 0;
        }
        return 0;
    }

    @Override
    public int reiknaRod(int n, Teningar teningar) {
        int[] rod = new int[5];
        int i = 0;
        for(int t:teningar.getTeningar()){
            rod[i] = t;
            i++;
        }  
        radaFylki(rod);
        int lengdRadar = finnaRod((n-5), rod);
        if(lengdRadar == 5){
            if (nuverandi == 1)
                stigin1[n] = 40;
            else 
                stigin2[n] = 40;
            return 40;
        }
        else if (lengdRadar == 4) {
            if (nuverandi == 1)
                stigin1[n] = 30;
            else 
                stigin2[n] = 30;
            return 30;
        }
        return 0;
    }
    
    private void radaFylki(int f[]){
        for(int i = 0; i < f.length-1; i++){
            for(int j = 0; j < f.length-1; j++){
                if(f[j] > f[j+1]) {
                    int temp = f[j];
                    f[j] = f[j+1];
                    f[j+1] = temp;
                }
            }
        }
    }
    
    private int finnaRod(int l, int f[]){
        int lengd = 1;
        for(int j = 0; j < f.length; j++){
            int curr = f[j];
            for(int i = 0; i < f.length;i++){
                if(curr+1 == f[i]) {
                    curr = f[i];
                    lengd++;
                }
            }
            if(l == lengd)
                return lengd;
            lengd = 1;
        }
        return 0;
    }

    @Override
    public int reiknaYahtzee(int n, Teningar teningar){
        boolean fimmEins = finnaAlvegEins(5, teningar);
        if(fimmEins) {
            if(nuverandi == 1)
                stigin1[n] = 50;
            else
                stigin2[n] = 50; 
            return 50;
        }
        return 0;
    }
    
    @Override
    public int reiknaFulltHus(int n, Teningar teningar){
        boolean thrirEins = finnaAlvegEins(3, teningar);
        boolean tveirEins = finnaAlvegEins(2, teningar);
        if(thrirEins&&tveirEins) {
            if(nuverandi == 1)
                stigin1[n] = 25;
            else
                stigin2[n] = 24;
            return 25;
        }
        return 0;
    }
    
    //Finnur hvort það eru nákvæmlega fjoldiEins eins teningar
    private boolean finnaAlvegEins(int fjoldiEins, Teningar teningar){
        for(int t: teningar.getTeningar()){
            int eins = 0;
            for(int e: teningar.getTeningar()){
                if (t == e)
                    eins++;
            }
            if(eins == fjoldiEins) {
                return true;
            }
        }
         return false;
    }
    
    @Override
    public int bonus(int s){
        if(s > 63){
            setBonus(35);
            return 35;
        }
        return 0;
    }
    
    private void setBonus(int b){
        if(nuverandi == 1)
            stigin1[13] = b;
        else 
            stigin2[13] = b;
    }
    
    @Override
    public int bonusSumma(){
        int s = 0;
        if(nuverandi == 1){
            for(int i = 0; i < 6; i++){
                s += stigin1[i];
            }
        } else {
            for(int i = 0; i < 6; i++){
                s += stigin2[i];
            }
        }
        return s;
    }
    
    @Override
    public boolean vinningur(){
        if(vin == 26)
            return true;
        return false;
    }
    
    @Override
    public int finnaSigurvegara(){
        if(summa1 == summa2){
            Random ran = new Random();
            int rand = ran.nextInt(2-1+1)+1;
            System.out.println(rand);
            return rand;
        }
        else if(summa1 < summa2){
            return 2;
        }else
            return 1;
    }
    
}
