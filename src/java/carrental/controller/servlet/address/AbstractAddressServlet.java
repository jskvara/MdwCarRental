package carrental.controller.servlet.address;

import carrental.controller.servlet.BaseServlet;
import carrental.model.entity.AddressEntity;
import carrental.model.service.ServiceException;
import carrental.model.service.AddressService;
import carrental.util.GuiceUtil;
import carrental.util.Message;
import java.io.IOException;

/**
 * Abstract class for common operations with Address
 */
public abstract class AbstractAddressServlet extends BaseServlet {
	AddressService addressService = GuiceUtil.getService(AddressService.class);

	protected Long getId() throws IOException {
		return getId("id", "/order");
	}

	protected AddressEntity getAddressEntity() throws IOException {
		AddressEntity addressEntity = null;

		String stringAddressId = request.getParameter("id");
		if (stringAddressId == null) {
			redirect("/address", "Wrong parameter.", Message.ERROR);
			return null;
		}

		try {
			Long addressId = Long.valueOf(stringAddressId);
			addressEntity = addressService.get(addressId);
			if (addressEntity == null) {
				throw new ServiceException("Address doesn't exist.");
			}
		} catch (ServiceException ex) {
			redirect("/address", ex.getMessage(), Message.ERROR);
			return null;
		} catch (NumberFormatException ex) {
			redirect("/address", "Wrong parameter.", Message.ERROR);
			return null;
		}

		return addressEntity;
	}
}
