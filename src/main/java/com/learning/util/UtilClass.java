package com.learning.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.Instant;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.learning.model.Cour;
import com.learning.model.Organization;
import com.learning.model.base.ConstantBase;

@Component
public class UtilClass {

	private static Logger LOGGER = LogManager.getLogger("UtilClass");

	public String constructPath(final Object entity, final String fileName, final Organization organization) {

		final String org = "organizasion_";
		if (entity instanceof Cour) {
			final Cour cour = (Cour) entity;

			return ConstantBase.PATH_FILE + File.separator + org + organization.getId() + File.separator + "courses"
					+ File.separator + cour.getId() + File.separator + fileName;
		}

		return "";
	}
	public boolean deleteFile(final File targetFile) {
		if (targetFile.exists()) {
			return targetFile.delete();
		}
		return false;
	}

	public void fileUploadWithFullPath(final InputStream fileCons, final String fullPath) {
		try {

			final byte[] data1 = new byte[fileCons.available()];

			fileCons.read(data1);

			final File targetFile = new File(fullPath);
			if (!targetFile.getParentFile().exists()) {
				targetFile.getParentFile().mkdirs();
			}

			final OutputStream outStream = new FileOutputStream(targetFile);
			outStream.write(data1);
			outStream.close();
		} catch (final IOException e) {
			LOGGER.error("Erreur fileUpload : {}", e);
		}
	}

}
