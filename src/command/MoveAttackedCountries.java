/*
import command.InputBanner;
import command.input.RelocateInput;
import java.util.ArrayList;
import map.Country;

package command;

import command.input.RelocateInput;
import java.util.ArrayList;
import map.Country;

public class MoveAttackedCountries extends Thread implements Runnable {
    ArrayList<Country> countriesToMove = new ArrayList<Country>();
    InputBanner banner;
    private boolean orderEntered = false;

    public MoveAttackedCountries(ArrayList<Country> countriesToMove, InputBanner banner){
        this.countriesToMove = countriesToMove;
        this.banner = banner;
    }

    @Override
    public void run(){
        System.out.println(countriesToMove);
        for (Country c : countriesToMove){
            banner = new InputBanner(banner.getMap(), c);
            banner.add(new Info(c + " relocates to"));
            RelocateInput input = new RelocateInput(banner, c);
            banner.add(input);
            banner.setLastVisible(input);
            while(!orderIsEntered()){
                try {
                    sleep(1);
                } catch (InterruptedException e) {
                }
            }
            orderEntered = false;
        }

        ArrayList<Country> countriesNotMoved = new ArrayList<Country>();
        for(Country c : countriesToMove){
            if(!c.getOrder().succeeds()){
                countriesNotMoved.add(c);
            }
        }
    }

    public void orderHasBeenEntered(){
        orderEntered = true;
    }

    private boolean orderIsEntered(){
        return orderEntered;
    }
}
*/