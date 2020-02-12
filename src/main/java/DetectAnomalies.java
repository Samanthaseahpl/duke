import java.util.ArrayList;

public class DetectAnomalies {

    public DetectAnomalies () {
    }

    public String checkForClash(ArrayList<Task> taskLists, String[] dateTimes) {
        String output;
        String sameTiming = "";
        String sameDay = "";
        boolean hasTime = !dateTimes[1].equals(" ");
        for (Task task : taskLists) {
            String item = task.toString();
            boolean hasSameDate = item.contains(dateTimes[0]);
            boolean hasSameTime = item.contains(dateTimes[1]) && hasTime;

            if (hasSameDate && hasSameTime) {
                sameTiming = sameTiming + item + "\n";
            }
//            else if (hasSameDate) {
//                sameDay = sameDay + item + "\n";
//            }
        }
        if (!sameTiming.equals("")) {
            output = "Clashes with: \n" + sameTiming;
        }
//        else if (!sameDay.equals("")) {
//            output = "May clash with these event on the same day: \n" + sameDay;
//        }
        else {
            output = "No Clashes.";
        }
        return output;
    }
}