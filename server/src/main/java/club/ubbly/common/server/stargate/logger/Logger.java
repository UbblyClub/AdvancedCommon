package club.ubbly.common.server.stargate.logger;

import alemiz.stargate.utils.StarGateLogger;
import dev.waterdog.plugin.Plugin;
import java.io.PrintWriter;
import java.io.StringWriter;

public class Logger implements StarGateLogger {

  private final Plugin loader;
  private boolean debug = false;

  public Logger(Plugin loader) {
    this.loader = loader;
  }

  public void setDebug(boolean debug) {
    this.debug = debug;
  }

  public boolean isDebug() {
    return this.debug;
  }

  @Override
  public void debug(String message) {
    if (this.debug) {
      this.loader.getLogger().info("[DEBUG] " + message);
    }
  }

  @Override
  public void info(String message) {
    this.loader.getLogger().info("§b" + message);
  }

  @Override
  public void warn(String message) {
    this.loader.getLogger().warning("§e" + message);
  }

  @Override
  public void error(String message) {
    this.loader.getLogger().warning("§c" + message);
  }

  @Override
  public void error(String message, Throwable e) {
    this.error(message);
    this.logException(e);
  }

  @Override
  public void logException(Throwable e) {
    StringWriter stringWriter = new StringWriter();
    PrintWriter printWriter = new PrintWriter(stringWriter);
    e.printStackTrace(printWriter);
    this.loader.getLogger().warning(stringWriter.toString());
  }
}
