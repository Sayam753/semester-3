import java.util.*;
import java.io.*;
import java.text.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
// Code by - Sayam Kumar
// Roll No - S20180010158
// Just compile and run. Every input is randomly generated
// Run the command: javac Assign08158.java && java Assign08158 > a.txt
// Now analyse the file a.txt

class Tags
{
    static Set<String> total_tags = new HashSet<String>();
    Set<String> tags_set;
    Tags()
    {
        tags_set = new HashSet<String>();
    }
}

class UserProfileSO extends Tags
{
    static List<UserProfileSO> all_users = new ArrayList<UserProfileSO>();
    String user_id;
    String user_profile_link;
    String name;
    int reputation_score;
    int gold;
    int silver;
    int bronze;

    void printDetails()
    {
        System.out.println(name+"("+gold+","+silver+","+bronze+")");
    }
}

class StackOverFlow extends Tags
{
    int votes;
    int number_of_answers;
    int number_of_views;
    String question;
    String question_id;
    String question_link;
    LocalDate date_posted;
    LocalTime time_posted;
    UserProfileSO user;
    
    StackOverFlow()
    {
        this.votes = 0;
        this.number_of_answers = 0;
        this.number_of_views = 0;
    }
    StackOverFlow(int a, int b, int c)
    {
        this.votes = a;
        this.number_of_answers = b;
        this.number_of_views = c;
    }
}

public class Assign08158
{
    public static UserProfileSO check_user(List<UserProfileSO> all_users, String data)
    {
        for(int i=0;i<UserProfileSO.all_users.size();i++)
        {
            UserProfileSO u = UserProfileSO.all_users.get(i);
            if (u.user_id.equals(data))
            {
                return u;
            }
        }
        return null;
    }

    static void findUser(int flag, int data) // question 2
    {
        int size = UserProfileSO.all_users.size();
        for(int i=0;i<size;i++)
        {
            UserProfileSO u = UserProfileSO.all_users.get(i);
            if (flag==1 && u.gold==data)
            {
                u.printDetails();
            }
            else if (flag==2 && u.silver==data)
            {
                u.printDetails();
            }
            else if((flag==3) && u.bronze == data)
            {
                u.printDetails();
            }
        }
    }

    static void findUser(int flag, int v1, int v2) // question 2
    {
        int size = UserProfileSO.all_users.size();
        for(int i=0;i<size;i++)
        {
            UserProfileSO u = UserProfileSO.all_users.get(i);
            if ((flag==4) && (u.gold==v1) && (u.silver==v2))
            {
                u.printDetails();
            }
            else if ((flag==5) && (u.silver==v1) && (u.bronze==v2))
            {
                u.printDetails();
            }
            else if ((flag==6) && (u.bronze==v1) && (u.gold==v2))
            {
                u.printDetails();
            }
        }
    }

    static void findUser(int flag, int v1, int v2, int v3) // question 2
    {
        int size = UserProfileSO.all_users.size();
        for(int i=0;i<size;i++)
        {
            UserProfileSO u = UserProfileSO.all_users.get(i);
            if ((u.gold==v1) && (u.silver==v2) && (u.bronze==v3))
                u.printDetails();
        }
    }

    static void findtags() // question 4
    {
        Random rand = new Random();
        System.out.println("Finding users with");
        int number_of_tags = Math.abs(rand.nextInt()%3)+1;
        List<String> all_tags = new ArrayList<String>();
        all_tags.addAll(Tags.total_tags);
        for(int i=all_tags.size()-1;i>=0;i--)
        {
            int index = Math.abs(rand.nextInt()%all_tags.size());
            String temp = all_tags.get(i);
            all_tags.set(i, all_tags.get(index));
            all_tags.set(index, temp);
        }

        // Getting random tags
        List<String> tags = new ArrayList<String>();
        System.out.print("Tags: ");
        for(int i=0;i<number_of_tags;i++)
        {
            tags.add(all_tags.get(i));
            System.out.print(all_tags.get(i) + ", ");
        }
        
        List<UserProfileSO> user_list = new ArrayList<UserProfileSO>();
        user_list.addAll(UserProfileSO.all_users);
        
        // Intersection of users for all tags
        for(int i=0;i<number_of_tags;i++)
        {
            String tag = tags.get(i);
            List<Integer> temp_user = new ArrayList<Integer>();
            for(int j=0;j<user_list.size();j++)
            {
                if (!user_list.get(j).tags_set.contains(tag))
                {
                    temp_user.add(j);
                }
            }
            for(int j=temp_user.size()-1;j>=0;j--)
            {
                user_list.remove((int)(temp_user.get(j)));
            }
        }

        // Printing all users
        System.out.println("\nNotes:");
        if (user_list.size()>0)
        {
            for(int i=0;i<user_list.size();i++)
                user_list.get(i).printDetails();
        }
        else
        {
            System.out.print("No user found");   
        }
    }

    public static void main(String[] args) throws Exception
    {
        ArrayList<StackOverFlow> questions_list = new ArrayList<StackOverFlow>();
        int f = 1;
        for(int z=0;z<100;z++)
        {
            StringBuilder file = new StringBuilder();
            file.append(Integer.toString(25145 + z) + ".html");
            System.out.println("File is " + file.toString());
            BufferedReader br = null;                                                                                                                                                                                                                                                  
            // try 
            // {                                                                                                                                                                                                                                                          
                br = new BufferedReader(new FileReader(file.toString()));
                String line = null;
                while ((line = br.readLine()) != null) 
                {
                    if (line.contains("vote-count-post"))
                    {
                        StackOverFlow new_question = new StackOverFlow();
                        // Getting number of votes
                        String[] votes = line.split("strong");
                        votes[1] = votes[1].replace(">", "");
                        votes[1] = votes[1].replace("</", "");
                        new_question.votes = Integer.parseInt(votes[1]);
                        System.out.println("Question Number = " + f + " " + "Votes = " + new_question.votes);
                        f+=1;
                        // Getting number of answers
                        while ((line = br.readLine()) != null)
                        {
                            if (line.contains("status"))
                            {
                                line = br.readLine();
                                String[] answers = line.split("strong");
                                answers[1] = answers[1].replace(">", "");
                                answers[1] = answers[1].replace("</", "");
                                new_question.number_of_answers = Integer.parseInt(answers[1]);
                                System.out.println("Answers = " + new_question.number_of_answers);
                                break;
                            }
                        }

                        // Getting number of views
                        while ((line = br.readLine()) != null)
                        {
                            if (line.contains("views"))
                            {
                                String[] views = line.split("title=\"");
                                views[1] = views[1].replace(" views\">", "");
                                views[1] = views[1].replace(",", "");
                                if (views[1].contains("k"))
                                {
                                    int value = (int)(Float.valueOf(views[1].replace("k", ""))*1000);
                                    new_question.number_of_views = value;
                                }
                                else
                                    new_question.number_of_views = Integer.valueOf(views[1]);
                                System.out.println("Views = " + new_question.number_of_views);
                                break;
                            }
                        }

                        // Getting question
                        while ((line = br.readLine()) != null)
                        {
                            if (line.contains("question-hyperlink"))
                            {
                                String[] question = line.split("/");
                                new_question.question_id = question[2];

                                String[] data = question[3].split("\"");
                                new_question.question_link = "https://stackoverflow.com/" + data[0];
                                data[3] = data[3].replace("<", "");
                                data[3] = data[3].replace(">", "");
                                new_question.question = data[3];

                                System.out.println("Question_id = " + new_question.question_id);
                                System.out.println("Question_link = " + new_question.question_link);
                                System.out.println("Question = " + new_question.question);
                                break;
                            }
                        }

                        // Getting tags
                        while ((line = br.readLine()) != null)
                        {
                            if (line.contains("class=\"tags"))
                            {
                                String[] data = line.split(" t-");
                                data[data.length-1] = data[data.length-1].replace("\">", "");
                                System.out.print("Tags = ");
                                for(int i=1; i<=data.length-1; i++)
                                {
                                    new_question.total_tags.add(data[i]);
                                    new_question.tags_set.add(data[i]);
                                    System.out.print(data[i] + " ");
                                }
                                System.out.print("\n");
                                break;
                            }
                        } 

                        // Getting date and time
                        while ((line = br.readLine()) != null)
                        {
                            if (line.contains("relativetime"))
                            {
                                String[] data = line.split("\"");
                                String[] post_info = data[1].split(" ");
                                
                                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                                new_question.date_posted = LocalDate.parse(post_info[0], dateFormat);

                                String[] timeString = post_info[1].split(":");
                                timeString[2] = timeString[2].replace("Z", "");
                                new_question.time_posted = LocalTime.of(Integer.parseInt(timeString[0]), Integer.parseInt(timeString[1]), Integer.parseInt(timeString[2]));

                                System.out.println("Date = " + new_question.date_posted + " Time = " + new_question.time_posted);
                                break;
                            }
                        }

                        // Getting user
                        while ((line = br.readLine()) != null)
                        {
                            if (line.contains("user-details"))
                            {
                                line = br.readLine();
                                if (!line.contains("<a href="))
                                {
                                    String u_id = line.replace("user", "").trim();
                                    UserProfileSO u = check_user(UserProfileSO.all_users, u_id);
                                    if(u == null)
                                    {
                                        u = new UserProfileSO();
                                        u.user_id = u_id;
                                        UserProfileSO.all_users.add(u);
                                    }
                                    u.name = line.trim();
                                    new_question.user = u;
                                    break;
                                }

                                String[] data = line.split("/");

                                // user already exists
                                UserProfileSO u = check_user(UserProfileSO.all_users, data[2]);
                                if (u == null)
                                {
                                    u = new UserProfileSO();
                                    u.user_id = data[2];
                                    String[] user_details = line.split("\"");
                                    u.user_profile_link = "https://stackoverflow.com" + user_details[1];
                                    int go = line.indexOf("\">") + 2;
                                    int finish = line.indexOf("</a>");
                                    u.name = line.substring(go, finish);

                                    // getting reputation
                                    line = br.readLine();
                                    line = br.readLine();
                                    String[] s = line.split("<span ");
                                    int start = s[1].indexOf("dir=\"ltr\">") + 10;
                                    int end = s[1].indexOf("</span>");
                                    s[1] = s[1].substring(start, end).replace(",", "");
                                    
                                    if (s[1].contains("k"))
                                    {
                                        int value = (int)(Float.valueOf(s[1].replace("k", ""))*1000);
                                        u.reputation_score = value;
                                    }
                                    else
                                        u.reputation_score = Integer.valueOf(s[1]);

                                    s = line.split("<span class=\"badgecount\">");
                                    int count = 0;
                                    if (line.contains("badge1")) // gold
                                    {
                                        u.gold = Integer.parseInt(s[1].substring(0, s[1].indexOf("</span>")));
                                        count += 1;
                                    }
                                    if (line.contains("badge2")) // silver
                                    {
                                        u.silver = Integer.parseInt(s[1+count].substring(0, s[1].indexOf("</span>")));
                                        count += 1;
                                    }
                                    if (line.contains("badge3")) // bronze
                                    {
                                        u.bronze = Integer.parseInt(s[1+count].substring(0, s[1].indexOf("</span>")));
                                    }
                                    u.tags_set.addAll(new_question.tags_set);
                                    UserProfileSO.all_users.add(u);
                                    
                                    System.out.println("UserName="+u.name + ", id=" +u.user_id + ", link=" + u.user_profile_link + ", reputation=" + u.reputation_score + "\nGold batches=" + u.gold + ", silver=" + u.silver + ", bronze=" + u.bronze + "\n\n");
                                }
                                new_question.user = u;
                                break;
                            }
                        }
                        questions_list.add(new_question);
                    }
                }
            // } 
            // catch(FileNotFoundException e) 
            // {                                                                                                                                                                                                                                                
            //     System.err.println("Caught FileNotFoundException: " + e.getMessage());                                                                                                                                                                                                          
            //     throw new RuntimeException(e);                                                                                                                                                                                                                                                  
            // } 
            // catch(IOException e) 
            // {                                                                                                                                                                                                                                                          
            //     System.err.println("Caught IOException: " + e.getMessage());                                                                                                                                                                                                                    
            // }
            // finally 
            // {                                                                                                                                                                                                                                                                        
            //     if (null != br) 
            //     { 
            //         try
            //         {
            //             br.close();
            //         }
            //         catch(IOException e)
            //         {
            //             System.err.println("Caught IOException: " + e.getMessage());
            //         } 
            //     }                                                                                                                                                                                                                                                 
            // }
        }

        // Sorting with respect to gold, silver and bronze ---------------------------------------------
        for(int i=0;i<UserProfileSO.all_users.size();i++)
        {
            UserProfileSO a = UserProfileSO.all_users.get(i);
            for(int j=i+1;j<UserProfileSO.all_users.size();j++)
            {
                UserProfileSO b = UserProfileSO.all_users.get(j);
                if (a.gold<b.gold)
                {
                    UserProfileSO t = UserProfileSO.all_users.get(i);
                    UserProfileSO.all_users.set(i, UserProfileSO.all_users.get(j));
                    a = UserProfileSO.all_users.get(j);
                    UserProfileSO.all_users.set(j, t);
                }
                else if (a.gold == b.gold)
                {
                    if (a.silver < b.silver)
                    {
                        UserProfileSO t = UserProfileSO.all_users.get(i);
                        UserProfileSO.all_users.set(i, UserProfileSO.all_users.get(j));
                        a = UserProfileSO.all_users.get(j);
                        UserProfileSO.all_users.set(j, t);
                    }
                    else if (a.silver == b.silver)
                    {
                        if (a.bronze < b.bronze)
                        {
                            UserProfileSO t = UserProfileSO.all_users.get(i);
                            UserProfileSO.all_users.set(i, UserProfileSO.all_users.get(j));
                            a = UserProfileSO.all_users.get(j);
                            UserProfileSO.all_users.set(j, t);
                        }
                    }
                }
            }
        }

        // Question - 2 --------------------------------------------------------------------------------
        System.out.println("\nQuestion - 2 --------------------------------------------------------------------------\n");
        Random rand = new Random();
        int check = Math.abs(rand.nextInt()%7)+1;
        int size = UserProfileSO.all_users.size();
        UserProfileSO u = UserProfileSO.all_users.get(Math.abs(rand.nextInt() % size));
        int v1, v2, v3;
        if (check>=1 && check<=3)
        {
            if (check==1)
            {
                System.out.println("Finding users with "+ u.gold + " gold badges");
                v1 = u.gold;
            }
            else if (check==2)
            {
                System.out.println("Finding users with "+ u.silver + " silver badges");
                v1 = u.silver;
            }
            else
            {
                System.out.println("Finding users with "+ u.bronze + " bronze badges");
                v1 = u.bronze;
            }
            findUser(check, v1);
        }
        else if (check>=4 && check<=6)
        {
            if (check==4)
            {
                System.out.println("Finding users with "+u.gold+" gold and "+u.silver+" silver badges");
                v1 = u.gold;
                v2 = u.silver;
            }
            else if (check==5)
            {
                System.out.println("Finding users with "+u.silver+" silver and "+u.bronze+" bronze badges");
                v1 = u.silver;
                v2 = u.bronze;
            }
            else
            {
                System.out.println("Finding users with "+u.gold+" gold and "+u.bronze+" bronze badges");
                v1 = u.bronze;
                v2 = u.gold;
            }
            findUser(check, v1, v2);
        }
        else
        {
            System.out.println("Finding users with "+u.gold+" gold, "+u.silver+" silver and "+u.bronze+" bronze badges");
            u.printDetails();
            findUser(check, u.gold, u.silver, u.bronze);   
        }

        // Question - 3 --------------------------------------------------------------------------------
        System.out.println("\nQuestion - 3 Inheritance applied successfully -----------------------------------------\n");

        // Question - 4 --------------------------------------------------------------------------------
        System.out.println("\nQuestion - 4 --------------------------------------------------------------------------\n");
        Assign08158.findtags();
    }
}