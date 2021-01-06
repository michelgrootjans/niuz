using System;
using Autofac;
using niuz.application.repositories;
using NSubstitute;

namespace niuz.application.services
{
  public class BaseTest
  {
    public IArticleRepository articles { get; } = Substitute.For<IArticleRepository>();
    public IAuthorRepository authors { get; } = Substitute.For<IAuthorRepository>();
    public IPaymentRepository payments { get; } = Substitute.For<IPaymentRepository>();
    public ITeaserRepository teasers { get; } = Substitute.For<ITeaserRepository>();


    public T Get<T>()
    {
      var builder = new ContainerBuilder();
      builder.RegisterAssemblyTypes(typeof(ArticleService).Assembly).Where(t => t.Namespace.EndsWith("services"))
        .AsSelf();

      builder.Register(c => articles).As<IArticleRepository>();
      builder.Register(c => authors).As<IAuthorRepository>();
      builder.Register(c => payments).As<IPaymentRepository>();
      builder.Register(c => teasers).As<ITeaserRepository>();

      var container = builder.Build();
      return container.Resolve<T>();
    }
  }
}
