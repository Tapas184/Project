package fis.his.dc.snap.service;

import fis.his.dc.snap.model.SnapModel;

public interface InterfaceSnap {

	public String saveSnap(SnapModel snap);
	
	public SnapModel getSnapData(Long id);
}
