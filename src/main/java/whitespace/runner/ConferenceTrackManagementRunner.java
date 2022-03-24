package whitespace.runner;

/**
 PROBLEM TWO: CONFERENCE TRACK MANAGEMENT
 You are planning a big software development conference and have received several
 proposals and you're having trouble fitting them into the time constraints of the day
 -- there are so many possibilities! So you write a program to do it for you.
 ● The conference has multiple tracks each of which has a morning and
 afternoon session.
 ● Each session contains multiple talks.
 ● Morning sessions begin at 9am and must finish by 12 noon, for lunch.
 ● Afternoon sessions begin at 1pm and must finish in time for the networking
 event.
 ● The networking event can start no earlier than 4:00 and no later than 5:00.
 ● No talk title has numbers in it.
 ● All talk lengths are either in minutes (not hours) or lightning (5 minutes).
 ● Presenters will be very punctual; there needs to be no gap between sessions.
 Note that depending on how you choose to complete this problem, your solution
 may give a different ordering or combination of talks into tracks. This is acceptable;
 you don’t need to exactly duplicate the sample output given here.

 Test input:
 Proper Unit Tests for Anyone 60min
 Why Python? 45min
 TDD for Embedded Systems 30min
 Dependency Management with Interpreted languages 45min
 Java 8, really end of life 45min
 From Java 8 to Java 12 lightning
 Managing Network Latency 60min
 BDD Gone Mad 45min
 Do you smell that? (Code Smells) 30min
 Open Office Space or Closets???? 30min
 Proper Pairing 45min
 Spring JuJu 60min
 Effective DSL (Domain Specific Languages) 60min
 Clojure... What Happened (on my project) 45min
 Effective Legacy Code Techniques... 30min
 Backends and FrontEnds in JavaScript? 30min
 CD on the Mainframe 60min
 Making Windows Development Enjoyable... 30min
 Modern Build Systems 30min

 Test output:
 Track 1:
 09:00AM Proper Unit Tests for Anyone 60min
 10:00AM Why Python? 45min
 10:45AM TDD for Embedded Systems 30min
 11:15AM Dependency Management with Interpreted languages 45min
 12:00PM Lunch
 01:00PM Effective DSL (Domain Specific Languages) 60min
 02:00PM Java 8, really end of life 45min
 02:45PM Proper Pairing 45min
 03:30PM Effective Legacy Code Techniques... 30min
 04:00PM Backends and FrontEnds in JavaScript? 30min
 04:30PM Modern Build Systems 30min
 05:00PM Networking Event
 Track 2:
 09:00AM Managing Network Latency 60min
 10:00AM Spring JuJu 60min
 11:00AM Do you smell that? (Code Smells) 30min
 11:30AM Open Office Space or Closets???? 30min
 12:00PM Lunch
 01:00PM BDD Gone Mad 45min
 01:45PM Clojure... What Happened (on my project) 45min
 02:30PM Making Windows Development Enjoyable... 30min
 03:00PM CD on the Mainframe 60min
 04:00PM From Java 8 to Java 12 lightning
 05:00PM Networking Event
 */
public class ConferenceTrackManagementRunner
{
}
