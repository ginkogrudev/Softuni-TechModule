using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(Csharp_Blog.Startup))]
namespace Csharp_Blog
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureAuth(app);
        }
    }
}
