package ctrls;

import dao.DayDAO;
import dao.ShiftDAO;
import java.util.List;
import javax.faces.bean.ManagedBean;
import model.Day;
import model.Shift;

@ManagedBean
public class DayMBean {
    private Day d = new Day();
    
    public Day getDays(){
        return d;
    }
    
    public void setDays(Day d){
        this.d = d;
    }
    
    public List<Day> getListFull(){
        DayDAO ddao = new DayDAO();
        try{
            return ddao.findAll();
        }finally{
            ddao.close();
        }        
    }
    
    public List<Shift> getLoadShifts(){
        ShiftDAO sdao = new ShiftDAO();
        try{
            return sdao.findAll();
        }finally{
            sdao.close();
            }        
        }
    
    /* think on how you're loading the shiftz list with shifts*/

    private List<Shift> shiftz;
    
    public List<Shift> getShiftz(){
        return shiftz;
    }
    
    public void setShiftz(List<Shift> shiftz){
        this.shiftz = shiftz;
    }
    
    public String save(){
        DayDAO ddao = new DayDAO();
        try{
            if(d.getId() == 0){
                d.setShifts(shiftz);
                ddao.create(d);
                d = new Day();
            }else{
                ddao.update(d);
            }
            
        }finally{
            ddao.close();
        }
        return null;
    }
    
    public String select(Day d){
        this.d = d;
        return null;
    }
    
    public String delete(Day d){
        DayDAO ddao = new DayDAO();
        try{
            ddao.delete(d);
        }finally{
            ddao.close();
        }
        return null;
    }
}