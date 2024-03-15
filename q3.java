#include <bits/stdc++.h>
#include <sstream>
#include <iostream>
using namespace std;


int add(string s) 
{
    int pending,done;
    string temp;
    vector<string> v;

    ifstream read;
    read.open("todo.txt");

    while(getline(read,temp))
    {
        v.push_back(temp);
    }
    read.close();

    bool check=false; 

    for(int i=0;i<v.size();i++)
    {
        if(s==v[i])
        check=true; 
    }

    if(check==false)
    {
        fstream write;
        write.open("todo.txt",ios::app);
        write<<s<<endl;
        cout<<"Added todo : "<<"\""<<s<<"\""<<endl;
        write.close();
    }
    else
    {
        ofstream write;
        write.open("todo.txt");
        
        for(int i=0;i<v.size();i++)
        {
            if(v[i]!=s)
            {
                write<<v[i]<<endl;
            }
        }

        write<<s<<endl;
        cout<<"Added todo : "<<"\""<<s<<"\""<<endl;
        write.close();
    }
    
    return 0;
}

int del(int k) 
{
    string s;
    vector<string> v;

    ifstream read;
    read.open("todo.txt");
    while(getline(read,s))
    {
        v.push_back(s);
    }
    read.close();

    if(k>v.size() ||k<1)
    {

        cout<<"ERROR...! task #"<<k<<" does not exist! No task is deletd!"<<endl;
    }
    else
    {
        ofstream write;
        write.open("todo.txt");
        for(int i=0;i<v.size();i++)
        {
            if(i!=k-1)
            write<<v[i]<<endl;
        }
        cout<<"Deleted todo #"<<k<<endl;
    }
    
    return 0;
}

int done(int k) 
{
    
    int year,month,date,z;
    vector<string> v;
    string s,x;

    time_t ttime= time(0);
    tm *local_time=localtime(&ttime);
    year=local_time->tm_year + 1900;
    month=local_time->tm_mon + 1;
    date=local_time->tm_mday;

   

    ifstream read;
    read.open("todo.txt");

    while(getline(read,s))
    {
        v.push_back(s);
    }
    read.close();

   

    if(k>v.size())
    {
        cout<<"ERROR...! task #"<<k<<" doesn't exist!"<<endl;
    } 
    else
    {
        ofstream write1,write2;
        write1.open("todo.txt");
        write2.open("done.txt",ios::app);
        for(int i=0;i<v.size();i++)
        {
            if(i==k-1)
            {
                x=v[i];
                cout<<"Marked todo #"<<k<<" as done."<<endl;
            }
            else
            {
                write1<<v[i]<<endl;
            }
        }
        write1.close();
        write2<<"x "<<year<<"-"<<month<<"-"<<date<<" "<<x<<endl;
        write2.close();
    }
    
    return 0;
}

int ls() 
{
    string s;
    vector<string> v;

    ifstream read;
    read.open("todo.txt");
    while(getline(read,s))
    {
        v.push_back(s);
    }
    read.close();

    if(v.size()==0)
    {
        cout<<"There are no pending todos."<<endl;
    }
    else
    {
        for(int i=v.size()-1;i>=0;i--)
        {
            s=v.back();
            v.pop_back();
            cout<<"["<<(i+1)<<"]"<<" "<<s<<endl;
        }
    }

    return 0;
}

int help() 
{
    

    cout<<"How to use this TODO APP :-"<<endl;
    cout<<"$ ./todoapp add \"todo item\"        #Add a new todo item"<<endl;
    cout<<"$ ./todoapp del NUMBER             #Delete a todo item"<<endl;
    cout<<"$ ./todoapp done NUMBER            #Complete a todo item"<<endl;
    cout<<"$ ./todoapp ls                     #Show remaining todo items"<<endl;
    cout<<"$ ./todoapp help                   #Show How to use"<<endl;
    cout<<"$ ./todoapp report                 #Display Statistics of the app."<<endl;

    return 0;
}

int report() 
{
    int pending =0,done=0;
    string s;
    int year,month,date;

    ifstream read;
    read.open("todo.txt");  
    if(read.is_open())
    {
        while(getline(read,s))
        {
            pending++;
        }
    }
    read.close();

    read.open("done.txt"); 
    if(read.is_open())
    {
        while(getline(read,s))
        {
            done++;
        }
    }
    read.close();

    

    time_t ttime= time(0);
    tm *local_time=localtime(&ttime);
    year=local_time->tm_year + 1900;
    month=local_time->tm_mon + 1;
    date=local_time->tm_mday;

    cout<<year<<"-"<<month<<"-"<<date<<" "<<"Pending Tasks : "<<pending<<"Completed Tasks : "<<done<<endl;

    return 0;
    
}

int main(int argc, char* argv[])
{
    string s, s2;
    int k=0;

    if(argc>1)
    s=argv[1];

    if(argc==1 || s=="help")
    {
        help(); 
    }
    else if(s=="add")
    {

        if(argc==2)
        {
            cout<<"ERROR...! Missing todo string! Nothing Added!"<<endl;
        }
        else
        {
            s2=argv[2];
            add(s2);
        }
        
    }
    else if(s=="del")
    {

        if(argc==2)
        {
            cout<<"ERROR...! Missing task number! No task is deletd!"<<endl;
        }
        else
        {
            s2=argv[2];
            k=stoi(s2); 
            del(k);
        }
        
    }
    else if(s=="report")
    {
        report(); 
    }
    else if(s=="done")
    {

        if(argc==2)
        {
            cout<<"ERROR...! Missing task number! No task is marked completed!"<<endl;
        }
        else
        {
            s2=argv[2];
            k=stoi(s2); 

            
            if(k>0)
            done(k);
            else
            {
                cout<<"ERROR...! todo #0 does not exist!"<<endl;
            }
            
        }
        
    }
    else if(s=="ls")
    {
        ls(); 
    }

    return 0;
}
