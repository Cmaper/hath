/*

Copyright 2008-2009 E-Hentai.org
http://forums.e-hentai.org/
ehentai@gmail.com

This file is part of Hentai@Home.

Hentai@Home is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Hentai@Home is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Hentai@Home.  If not, see <http://www.gnu.org/licenses/>.

*/

package org.hath.base;

import java.lang.Thread;

public class CakeSphere implements Runnable {
	private Thread myThread;
	private ServerHandler handler;
	private HentaiAtHomeClient client;
	
	public CakeSphere(ServerHandler handler, HentaiAtHomeClient client) {
		myThread = new Thread(this);
		this.handler = handler;
		this.client = client;
	}
	
	public void stillAlive() {
		// Cake and grief counseling will be available at the conclusion of the test.
		myThread.start();
	}
	
	public void run() {
		ServerResponse sr = ServerResponse.getServerResponse(ServerHandler.ACT_STILL_ALIVE, handler);
		if(sr.getResponseStatus() == ServerResponse.RESPONSE_STATUS_OK) {
			Out.debug("Successfully performed a stillAlive test for the server.");
		}
		else if(sr.getResponseStatus() == ServerResponse.RESPONSE_STATUS_NULL) {
			Out.warning("Failed to connect to the server for the stillAlive test. This is probably a temporary connection problem.");
		}
		else {
			Out.warning("Failed stillAlive test (" + sr.getFailCode() + ")");		
			client.shutdown();
		}	
	}
}
