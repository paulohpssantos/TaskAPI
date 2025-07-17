package util;

import java.io.Serializable;

public interface IModel <ID extends Serializable> extends Serializable {
	ID getId();
}
