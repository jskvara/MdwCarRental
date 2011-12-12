package carrental.controller.rest;

import carrental.controller.rest.converter.VehicleRestConverter;
import carrental.controller.rest.converter.VehiclesListRestConverter;
import carrental.model.entity.VehicleEntity;
import carrental.model.service.ServiceException;
import carrental.model.service.VehicleService;
import carrental.util.GuiceUtil;
import com.sun.jersey.api.NotFoundException;
import java.net.URI;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

/**
 * Vehicle web service resource
 */
@Path("vehicle")
public class VehicleResource {

	@Context
	private UriInfo uriInfo;

//	@Context
//	private Request request;

	protected VehicleService service = GuiceUtil.getService(VehicleService.class);

	@GET
	@Produces({"application/json", "application/xml"})
	public VehiclesListRestConverter getAll() {
		List<VehicleEntity> vehicles = service.getAll();
		VehiclesListRestConverter vehiclesListConverter = new VehiclesListRestConverter(vehicles);

		return vehiclesListConverter;
	}

	@GET
	@Produces({"application/json", "application/xml"})
	@Path("{id: \\d+}")
	public VehicleRestConverter get(@PathParam("id") Long id
	/*, @DefaultValue("2") @QueryParam("step") int step,
			 @Context SecurityContext sc) {
    if (sc.isUserInRole("PreferredCustomer") {*/) {
		VehicleEntity vehicle = service.get(id);

		if (vehicle == null) {
			throw new NotFoundException("Vehicle "+ id +" was not found.");
		}

		return new VehicleRestConverter(vehicle);
	}

	@POST
	@Consumes({"application/xml", "application/json"})
	public Response add(JAXBElement<VehicleRestConverter> vehicleInput) {
		VehicleEntity vehicle= vehicleInput.getValue().getEntity();
		if (vehicle.getId() != null) {
			return Response.status(Response.Status.CONFLICT).build();
		}

		Long id;
		try {
			id = service.save(vehicle);
		} catch (ServiceException ex) {
//			System.out.println("E: "+ ex.getMessage());
			return Response.status(Response.Status.BAD_REQUEST)
					.entity(ex.getMessage()).build();
		}

        URI vehicleUri = uriInfo.getAbsolutePathBuilder().path(id.toString()).build();

        return Response.created(vehicleUri).build();
    }

	@PUT
	@Consumes({"application/xml","application/json"})
//	@Path("{id: \\d+}")
	public Response edit(JAXBElement<VehicleRestConverter> vehicleInput/*,
			@PathParam("id") Long id*/) {
		VehicleEntity vehicle = vehicleInput.getValue().getEntity();
		System.out.println("V:"+ vehicle);
//		if (vehicle.getId() == null) {
//			return Response.status(Response.Status.NOT_ACCEPTABLE).build();
//		}

		Long id;
		try {
			id = service.save(vehicle);
		} catch (ServiceException ex) {
//			System.out.println("E: "+ ex.getMessage());
			return Response.status(Response.Status.BAD_REQUEST)
					.entity(ex.getMessage()).build();
		}

		URI vehicleUri = uriInfo.getAbsolutePathBuilder().path(id.toString()).build();

        return Response.created(vehicleUri).build();
    }

	@DELETE
	@Path("{id: \\d+}")
	public Response delete(@PathParam("id") Long id) {
		try {
			service.delete(id);
		} catch (ServiceException ex) {
			return Response.status(400).entity(ex.getMessage()).build();
		}

		return Response.ok().build();
	}
}
