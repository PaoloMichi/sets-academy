package it.sets.common.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.sets.common.exception.FtpException;

public class FtpClientUtils {
	
	private final Logger LOGGER = LoggerFactory.getLogger(FtpClientUtils.class);
	
	private String server;
    private int port;
    private String user;
    private String password;
    private FTPClient ftp;

    public FtpClientUtils(String server, int port, String user, String password) {
		this.server = server;
		this.port = port;
		this.user = user;
		this.password = password;
	}

	public void open() throws IOException {
        ftp = new FTPClient();

        ftp.setConnectTimeout(20000);
        ftp.setDataTimeout(20000);
        ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));

        ftp.connect(server, port);
        int reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            throw new IOException("Exception in connecting to FTP Server");
        }

        ftp.login(user, password);
        LOGGER.debug("FtpClientUtils: Login ok");
    }
	
    public void close() throws IOException {
        ftp.disconnect();
        LOGGER.debug("FtpClientUtils: disconnect ok");
    }
    
    public Collection<String> list() throws IOException {
        return this.list("");
    }
    
    public Collection<String> list(String path) throws IOException {
    	
        ftp.enterLocalPassiveMode();
        FTPFile[] files = ftp.listFiles(path);
        LOGGER.debug("FtpClientUtils: listFiles ok");
        return Arrays.stream(files)
          .map(FTPFile::getName)
          .collect(Collectors.toList());
    }

    public Collection<String> listWrap() {
    	return this.listWrap("");
    }
    
    public Collection<String> listWrap(String path) {
    	
    	try {
			this.open();
		} catch (IOException e) {
			try {
				this.close();
			} catch (IOException e1) {
				throw new FtpException("Exception in closing to FTP Server", e);
			}
			throw new FtpException("Exception in connecting to FTP Server", e);	
		}
    	Collection<String> list;
		try {
			list = this.list(path);
		} catch (IOException e) {
			try {
				this.close();
			} catch (IOException e1) {
				throw new FtpException("Exception in closing to FTP Server", e1);
			}
			throw new FtpException("Exception in listing elements from FTP Server", e);
		}
        try {
			this.close();
		} catch (IOException e) {
			throw new FtpException("Exception in closing to FTP Server", e);
		}
        return list;
    }
    
    public void upload(String remoteFileName, String localFilePath) throws IOException {
    	
    	ftp.enterLocalPassiveMode();
        try (FileInputStream in = new FileInputStream(localFilePath)) {
        	ftp.storeFile(remoteFileName, in);
        }
        
    }
    
    public void uploadWrap(String remoteFileName, String localFilePath) {
    	
    	try {
			this.open();
		} catch (IOException e) {
			try {
				this.close();
			} catch (IOException e1) {
				throw new FtpException("Exception in closing to FTP Server", e);
			}
			throw new FtpException("Exception in connecting to FTP Server", e);
		}
		try {
			this.upload(remoteFileName, localFilePath);
		} catch (IOException e) {
			try {
				this.close();
			} catch (IOException e1) {
				throw new FtpException("Exception in closing to FTP Server", e);
			}
			throw new FtpException("Exception in uploading element to FTP Server", e);
		}
        try {
			this.close();
		} catch (IOException e) {
			throw new FtpException("Exception in closing to FTP Server", e);
		}
    }
    
    public boolean retrieve(String remoteFileName, String localFilePath) throws IOException {

    	ftp.enterLocalPassiveMode();
        try (FileOutputStream out = new FileOutputStream(localFilePath)) {
        	return ftp.retrieveFile(remoteFileName, out);
        }

    }
    
    public boolean retrieveWrap(String remoteFileName, String localFilePath) {
    	
    	boolean retrieve = false;
    	try {
			this.open();
		} catch (IOException e) {
			try {
				this.close();
			} catch (IOException e1) {
				throw new FtpException("Exception in closing to FTP Server", e);
			}
			throw new FtpException("Exception in connecting to FTP Server", e);
		}
		try {
			retrieve = this.retrieve(remoteFileName, localFilePath);
		} catch (IOException e) {
			try {
				this.close();
			} catch (IOException e1) {
				throw new FtpException("Exception in closing to FTP Server", e);
			}
			throw new FtpException("Exception in retrieving element from FTP Server", e);
		}
        try {
			this.close();
		} catch (IOException e) {
			throw new FtpException("Exception in closing to FTP Server", e);
		}
        return retrieve;
    }
    
    public void delete(String remoteFileName) throws IOException {
    	ftp.deleteFile(remoteFileName);
    }
    
    public void deleteWrap(final String remoteFileName) {
    	try {
			this.open();
		} catch (IOException e) {
			try {
				this.close();
			} catch (IOException e1) {
				throw new FtpException("Exception in closing to FTP Server", e);
			}
			throw new FtpException("Exception in connecting to FTP Server", e);
		}
		try {
	    	ftp.deleteFile(remoteFileName);
		} catch (IOException e) {
			try {
				this.close();
			} catch (IOException e1) {
				throw new FtpException("Exception in closing to FTP Server", e);
			}
			throw new FtpException("Exception in retrieving element from FTP Server", e);
		}
        try {
			this.close();
		} catch (IOException e) {
			throw new FtpException("Exception in closing to FTP Server", e);
		}
    }

}
