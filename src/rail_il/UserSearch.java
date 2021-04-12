package rail_il;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.Set;

public class UserSearch {

	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		Managementable uiManager = new ConsoleUI();
		ObjectInputStream inFile = new ObjectInputStream(new FileInputStream("/Rail_IL/rail.dat"));
		uiManager.setAllRides((Set<Ride>)inFile.readObject());
		inFile.close();
		String time = args[1] + ":" + args[2];
		uiManager.searchRide(args[0], LocalTime.parse(time), args[3]);
		
		if(args[4].equalsIgnoreCase("html"))
				SearchRideByUserHTML(uiManager.getSearchResult());
		else
			SearchRideByUser(uiManager);
	}
	

	public static void SearchRideByUserHTML(Ride[] searchResult){
		
		System.out.print("<h2 style='text-align: center;'><span style='color: #ff6600;'>search result</span></h2><p>&nbsp;</p>");//search result
		
		if(searchResult[0] == null ){
			System.out.print("<p style='text-align: center;'><img src='https://html-online.com/editor/tinymce4_6_5/plugins/emoticons/img/smiley-frown.gif' alt='frown' />We didn't find a trip that matches your search<img src='https://html-online.com/editor/tinymce4_6_5/plugins/emoticons/img/smiley-frown.gif' alt='frown' /></p>");
			return;
		}
		
		for(int i = 0 ; i < searchResult.length ; i++){
			if(searchResult[i] == null)
				break;
			System.out.print("<h3 style='text-align: left;'>&nbsp;<span style='text-decoration: underline;'><span style='color: #ff0000; text-decoration: underline;'>Ride Number " + (i+1) + "</span></span> </h3>");
			Iterator<Station> itr = searchResult[i].getAllStations().iterator(); 
			System.out.print("<table style='height: 33px;' width='575'><tbody><tr>");
			System.out.print("<td style='width: 184.333px; text-align: center;'><h3><strong>Time</strong></h3></td>");
			System.out.print("<td style='width: 184.333px; text-align: center;'><h3><strong>Station Name</strong></h3></td>");
			System.out.print("<td style='width: 184.333px; text-align: center;'><h3><strong>Type</strong></h3></td></tr>");
			
			while(itr.hasNext()){
				Station temp = itr.next();
					if(temp.getStationType().equals(Station.eType.DESTINATION))
					System.out.print("<tr><td style='width: 184.333px; text-align: center;'><p>" + temp.getTime() + " (arrivel)<p></td>");
				else
					System.out.print("<tr><td style='width: 184.333px; text-align: center;'><p>" + temp.getTime() + "<p></td>");
				System.out.print("<td style='width: 184.333px; text-align: center;'><p>" + temp.getName() + "<p></td>");
				System.out.print("<td style='width: 184.333px; text-align: center;'><p>" + temp.getStationType().toString() + "<p></td>");
				}
			
			System.out.print("</tr></tbody></table><p>&nbsp;</p>");
		}
	}
	public static void SearchRideByUser(Managementable uiManager){
		
		
		System.out.println("We found " + uiManager.getNumOfResult() + " rides that matching your search..");
		
		for(int i = 0 ; i < uiManager.getNumOfResult() ; i++)
			System.out.println("\nRide #" + (i+1) + ":\n" + uiManager.getSearchResult()[i].toString());
		
	}

}
