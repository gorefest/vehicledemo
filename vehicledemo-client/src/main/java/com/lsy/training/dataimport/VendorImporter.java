package com.lsy.training.dataimport;

import java.io.File;

import com.lsy.training.dto.Dto;
import com.lsy.training.dto.VendorDto;
import com.lsy.training.ws.VendorWebService;

public class VendorImporter extends AbstractImporter<VendorDto> {

    VendorWebService vendorWebService;


    public VendorImporter() {
		
	}

    @Override
    public Long saveDto(VendorDto dto) {
        return vendorWebService.saveVendor(dto);
    }

    public VendorImporter(AbstractRowMapper rowMapper, FileScroller fileScroller,
			VendorWebService vendorWebService, File file) {
		super();
		this.rowMapper = rowMapper;
		this.fileScroller = fileScroller;
		this.vendorWebService = vendorWebService;
		this.file = file;
	}


	
	
}
