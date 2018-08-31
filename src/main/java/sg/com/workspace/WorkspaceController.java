package sg.com.workspace;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.com.workspace.request.WorkspaceRequest;

@RestController
@RequestMapping("/workspace")
public class WorkspaceController {
	@Autowired 
	WorkspaceService service;


	@GetMapping
	public List<WorkspaceModel> getAllWorkspace(){
		return service.geAlltWorkspace();
	}
	
	/**
	 * 
	 * @param 	wsid Workspace Id
	 * @return 	HttpStatus 202 Accepted 
	 * 			HttpStatus 404 not found.
	 * Json:
	 	{
    		"id": "5b8497035cb1d13ec49984d3",
    		"wsName": "w1",
    		"userId": [
        		"S1234567A",
        		"S1234567C",
        		"S1234567E",
        		"S1234567F",
        		"S1234567I"
    			],
    		"createdBy": "Somebody",
    		"createdDate": "2018-08-28T00:27:47.732+0000",
    		"modifiedDate": "2018-08-28T00:27:47.732+0000"
		}
	 */
	@GetMapping("/id/{wsid}")
	public ResponseEntity<WorkspaceModel> getWorkspaceById(@PathVariable("wsid") String wsid){
			Optional<WorkspaceModel> result =  service.getWorkspaceById(wsid);	
			if(result.isPresent()){
				return ResponseEntity.accepted().body(result.get());
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	/**
	 * 
	 * @param 	name workspace's name
	 * @return 	HttpStatus 202 Accepted or 404 not found.
	 * return 	Json:
	 * {
    	"id": "5b8497035cb1d13ec49984d3",
    	"wsName": "w1",
    	"userId": [
        	"S1234567A",
        	"S1234567C",
        	"S1234567E",
        	"S1234567F",
        	"S1234567I"
    	],
    	"createdBy": "Somebody",
    	"createdDate": "2018-08-28T00:27:47.732+0000",
    	"modifiedDate": "2018-08-28T00:27:47.732+0000"
		}
	 */
	@GetMapping("/name/{name}")
	public ResponseEntity<List<WorkspaceModel>> getWorkspaceByName(@PathVariable("name") String name){
			List<WorkspaceModel> result =  service.getWorkspaceByName(name);	
			if(result.isEmpty()){
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

			}
			return ResponseEntity.accepted().body(result);

	}
	
	/**
	 * 
	 * @param 	requestObj	Json object below
	 * @return	HttpStatus 202 Accepted 
 	 *			HttpStatus 400 Bad Request when id is already used.
	 * 
	 * { 
			"wsName": "w3",
			"userId": ["S1234567D"]
		}
	 */
	@PostMapping()
	public ResponseEntity<WorkspaceModel> createWorkspace(@Valid @RequestBody WorkspaceRequest requestObj ){
		List<WorkspaceModel> result = service.getWorkspaceByName(requestObj.getWsName());
		if(!result.isEmpty()){
			return ResponseEntity.status(HttpStatus.IM_USED).build();
		}
		return ResponseEntity.accepted().body(service.createWorkspace(requestObj));
	}
	/**
	 * 
	 * @param 	requetObj Json object below
	 * @return 	HttpStats 202 Accepted Or 400 Bad request when id is already used.
	 * { 
			"id": "5b849e1c5cb1d14b2055e47d",
			"wsName": "u3",
			"userId": ["S1234567D"]
		}
	 */
	@PutMapping()
	public ResponseEntity<Void> updateWorkspace(@Valid @RequestBody WorkspaceRequest requetObj ){
		if(service.updateWorkspace(requetObj)){
			return ResponseEntity.accepted().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

	}
	/**
	 * 
	 * @param 	wsid
	 * @return 	HttpStats 202 Accepted Or 404 Not Found.
	 */
	@DeleteMapping("/{wsname}")
	public ResponseEntity<Void>  deleteWorkspace(@PathVariable String wsname)
	{
		if(service.deleteWorkspace(wsname)){
			return ResponseEntity.accepted().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		

	}
	
	/**
	 * 
	 * @param id 	IC number of user
	 * @return 		HttpStats 202 Accepted Or 404 Not Found.
	 */
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<WorkspaceModel>> getWorkspaceForUser(@PathVariable("userId") String id){
		if(service.getUserWorkspace(id).isEmpty()){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		}
		return ResponseEntity.accepted().body(service.getUserWorkspace(id));

	}
	
	/**
	 * 
	 * @param userId 	IC number of user
	 * @param wsId 		WorkspaceId to remove the user from
	 * @return 			HttpStats 202 Accepted Or 404 Not Found.

	 */
	@DeleteMapping("/user/{userId}/{wsId}")
	public ResponseEntity<Void> deleteUserFromWorkspace(@PathVariable("userId") String userId,
														@PathVariable("wsId") String wsId){
		if(service.deleteUserFromWorkspace(userId, wsId)){
			return ResponseEntity.accepted().build();
		}	
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
 
		
	}

}
