package sg.com;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import sg.com.account.IUserRepos;
import sg.com.account.UserModel;
import sg.com.item.IItemRepos;
import sg.com.item.ItemModel;
import sg.com.item.entity.CheckList;
import sg.com.item.entity.Comment;
import sg.com.item.entity.EStatus;
import sg.com.item.entity.ETopicType;
import sg.com.workspace.IWorkspaceRepo;
import sg.com.workspace.WorkspaceModel;

@Component
public class MyCommandLineRunner implements CommandLineRunner{
	@Autowired IUserRepos accountRepo;
	@Autowired IWorkspaceRepo workspaceRepo;
	@Autowired IItemRepos itemRepo;
	@Autowired PasswordEncoder passwordEncoder;

	
	@Override
	public void run(String... args) throws Exception {
	/*	accountRepo.deleteAll();
		workspaceRepo.deleteAll();
		itemRepo.deleteAll();*/
		
		createUser();
		createWorkspace();
		createItem(ETopicType.TASK, "MyTask", "w1" );
		createItem(ETopicType.INCIDENT, "MyIncident", "w1" );
		createItem(ETopicType.ISSUE, "MyIssue", "w2" );
		createItem(ETopicType.POST, "MyPost", "w2" );

		UpdateTask();
	}

	public void createUser(){
		
		UserModel u1 = createUserService( "Amelia", "S1234567A", "Project Manager",  "Amelia@com.sg",  "CompanyA");
		UserModel u2 = createUserService( "Bella",  "S1234567B",  "Project Manager",  "Bella@com.sg",  "CompanyB");
		UserModel u3 = createUserService( "Claire",  "S1234567C",  "Team Lead",  "Claire@com.sg",  "CompanyA");
		UserModel u4 = createUserService( "Daniel",  "S1234567D",  "Team Lead",  "Daniel@com.sg",  "Companyb");
		UserModel u5 = createUserService( "Ethan",  "S1234567E",  "Developer",  "Ethan@com.sg",  "CompanyA");
		UserModel u6 = createUserService( "Faye",  "S1234567F",  "Developer",  "Faye@com.sg",  "CompanyA");
		UserModel u7 = createUserService( "Gabby",  "S1234567G",  "Developer",  "Gabby@com.sg",  "CompanyB");
		UserModel u8 = createUserService( "Henry",  "S1234567H",  "Developer",  "Henry@com.sg",  "CompanyB");
		UserModel u9 = createUserService( "Isaac",  "S1234567I",  "Administrator",  "Isaac@com.sg",  "CompanyC");
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

	public UserModel createUserService(String name, String ic, String designation, 
			String email, String organization ){
		UserModel member = new UserModel();
		member.setIcNumber(ic);
		member.setDesignation(designation);
		member.setEmail(email);
		member.setName(name);
		member.setOrganization(organization);
		
		member.setHp(1234567);
		member.setPassword(passwordEncoder.encode("pass"));
		member.setAuthorities(AuthorityUtils.createAuthorityList("ROLE_USER"));
		return member;
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

		WorkspaceModel w1 = new WorkspaceModel("w1", l1, "S1234567A");
		
		WorkspaceModel w2 = new WorkspaceModel("w2", l2, "S1234567B");
		workspaceRepo.save(w1);
		workspaceRepo.save(w2);
	}

	
	public void createItem(ETopicType type, String title, String workspace){
		ItemModel t1 = new ItemModel();
		//t1.setDetail(detail); // base on ui design task does not have detail
		//t1.setLocation(location); 
		//t1.setTag();
	
		t1.setTitle(title);
		t1.setWsname(workspace); 
		t1.setType(type); 
	
		List l1 = new ArrayList();
		l1.add("S1234567C");
		l1.add("S1234567B");
		
		t1.setReceiver(l1);
		t1.setStatus(EStatus.NEW);
		t1.setDueDate(Date.from(Instant.now()));
		//t1.setAssignees(l1);
		
		CheckList cl1 = new CheckList();
		cl1.setDescription("do somthing now");
		cl1.setDone(false);
		List l2 = Arrays.asList(cl1);
		t1.setChecklist(l2);
		
		Comment cm1 = new Comment();
		cm1.setComment("Comment added : "  + Instant.now());
		List l3 = Arrays.asList(cm1);
		t1.setComments(l3);
		itemRepo.save(t1);
	}
	public void UpdateTask(){
		List<ItemModel> list = itemRepo.findByTitle("MyTask");
		ItemModel i = list.get(0);
		i.setTitle("Task1a");
		itemRepo.save(i);
	}
	
	
}
