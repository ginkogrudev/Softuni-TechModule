using Microsoft.Owin;
using Owin;
using System.Data.Entity;
using Csharp_Blog.Migrations;
using Csharp_Blog.Models;

[assembly: OwinStartupAttribute(typeof(Csharp_Blog.Startup))]
namespace Csharp_Blog
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            Database.SetInitializer(
                new MigrateDatabaseToLatestVersion<BlogDbContext, Configuration>());

            ConfigureAuth(app);
        }
    }
}
