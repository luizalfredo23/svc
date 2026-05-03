package br.com.luizalfredo23;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/travel")
public class TravelAgentResource {
	@Inject
	TravelAgentAssistant assistant;

	@Inject
	PackageExpert expert;

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/assistant")
	public String askAssistant(String question) {
		return assistant.chat(question);
	}

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/expert")
	public String ask(String question, @HeaderParam("X-User-Name") String userName) {
		if (userName != null && !userName.isEmpty()) {
			try {
				SecurityContext.setCurrentUser(userName);
				return expert.chat(userName, question); // Usar userName como memoryId
			} finally {
				SecurityContext.clear();
			}
		} else {
			return "Usuário precisa estar autenticado!";
		}
	}
}