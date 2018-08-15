package sg.com;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import sg.com.account.IAccountRepos;
import sg.com.account.User;
import sg.com.item.IItemRepos;
import sg.com.item.entity.CheckList;
import sg.com.item.entity.Comment;
import sg.com.item.entity.EStatus;
import sg.com.item.entity.Item;
import sg.com.workspace.IWorkspaceRepo;
import sg.com.workspace.Workspace;

@Component
public class MyCommandLineRunner implements CommandLineRunner{
	@Autowired IAccountRepos accountRepo;
	@Autowired IWorkspaceRepo workspaceRepo;
	@Autowired IItemRepos itemRepo;
	
	
	@Override
	public void run(String... args) throws Exception {
		accountRepo.deleteAll();
		workspaceRepo.deleteAll();
		itemRepo.deleteAll();
		
		createUser();
		createWorkspace();
		createTask();
		UpdateTask();
	}
	
	public void createUser(){
		
		User u1 = new User( "Amelia",  "S1234567A",  "Project Manager",  "Amelia@com.sg",  "CompanyA",  "img",  1234567);
		User u2 = new User( "Bella",  "S1234567B",  "Project Manager",  "Bella@com.sg",  "CompanyB",  "img",  1234567);
		User u3 = new User( "Claire",  "S1234567C",  "Team Lead",  "Claire@com.sg",  "CompanyA",  "img",  1234567);
		User u4 = new User( "Daniel",  "S1234567D",  "Team Lead",  "Daniel@com.sg",  "Companyb",  "img",  1234567);
		User u5 = new User( "Ethan",  "S1234567E",  "Developer",  "Ethan@com.sg",  "CompanyA",  "img",  1234567);
		User u6 = new User( "Faye",  "S1234567F",  "Developer",  "Faye@com.sg",  "CompanyA",  "img",  1234567);
		User u7 = new User( "Gabby",  "S1234567G",  "Developer",  "Gabby@com.sg",  "CompanyB",  "img",  1234567);
		User u8 = new User( "Henry",  "S1234567H",  "Developer",  "Henry@com.sg",  "CompanyB",  "img",  1234567);
		User u9 = new User( "Isaac",  "S1234567I",  "Administrator",  "Isaac@com.sg",  "CompanyC",  "img",  1234567);
		accountRepo.save(u1);
		accountRepo.save(u2);
		accountRepo.save(u3);
		accountRepo.save(u4);
		accountRepo.save(u5);
		accountRepo.save(u6);
		accountRepo.save(u7);
		accountRepo.save(u8);
		accountRepo.save(u9);

	}

	public void createWorkspace(){
		List<String> l1 = new ArrayList<String>();
		l1.add("S1234567A");
		l1.add("S1234567C");
		l1.add("S1234567E");
		l1.add("S1234567F");
		l1.add("S1234567I");

		List<String> l2 = new ArrayList<String>();
		l2.add("S1234567B");
		l2.add("S1234567D");
		l2.add("S1234567G");
		l2.add("S1234567H");
		l2.add("S1234567I");

		Workspace w1 = new Workspace("w1", l1, "S1234567A");
		Workspace w2 = new Workspace("w2", l2, "S1234567B");
		workspaceRepo.save(w1);
		workspaceRepo.save(w2);
	}

	public void createTask(){
		Item t1 = new Item();
		t1.setTitle("Task1");
		//t1.setDetail(detail); // base on ui design task does not have detail
		//t1.setLocation(location); 
		t1.setWsid("w1"); 
		//t1.setType(ETopicType.TASK); should be handle by controller
		List l1 = new ArrayList();
		l1.add("S1234567C");
		t1.setReceiver(l1);
		t1.setStatus(EStatus.NEW);
		t1.setDueDate(Date.from(Instant.now()));
		//t1.setTag();
		t1.setAssignees(l1);
		CheckList cl1 = new CheckList();
		cl1.setDescription("do somthing now");
		cl1.setDone(false);
		List l2 = Arrays.asList(cl1);
		t1.setChecklist(l2);
		Comment cm1 = new Comment("my comment");
		List l3 = Arrays.asList(cm1);
		t1.setComments(l3);
		itemRepo.save(t1);
	}
	public void UpdateTask(){
		List<Item> list = itemRepo.findByTitle("Task1");
		Item i = list.get(0);
		i.setTitle("Task1a");
		itemRepo.save(i);
	}
	
	
}
